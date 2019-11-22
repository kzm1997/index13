package cn.kzm.web.servlet;

import cn.kzm.domain.Category;
import cn.kzm.service.CategoryService;
import cn.kzm.service.serviceImpl.CategoryServiceImpl;
import cn.kzm.utils.JedisUtil;
import cn.kzm.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 热点信息展示
 */
@WebServlet(name = "CategoryServlet",
urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends BaseServlet {
    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        String JsonStr=jedis.get("allCats");
        if (null==JsonStr||"".equals(JsonStr)){
            //调用业务层获取全部分类
            CategoryService categoryService=new CategoryServiceImpl();
            List<Category>list= categoryService.getAllCats();
            //将全部分类转换为json格式
             JsonStr = JSONArray.fromObject(list).toString();
             jedis.set("allCats",JsonStr);
            //响应到客户端
            //告诉浏览器本次响应的是Json格式的字符串
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(JsonStr);
        }else {
            System.out.println("从Jedis缓存中取数据");
            //响应到客户端
            //告诉浏览器本次响应的是Json格式的字符串
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(JsonStr);
        }
        JedisUtil.closeJedis(jedis);


        return null;
    }

}
