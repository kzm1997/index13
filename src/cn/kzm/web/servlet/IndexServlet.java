package cn.kzm.web.servlet;

import cn.kzm.domain.Product;
import cn.kzm.service.ProductService;
import cn.kzm.service.serviceImpl.ProductServiceImpl;
import cn.kzm.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用业务层功能，获取全部的分类信息，返回集合
        //调用业务层查询最新商品，查询最热商品，返回2个集合
        //将2个集合放到request
        ProductService productService = new ProductServiceImpl();
        List<Product> list01 = productService.findNews();
        List<Product> list02 = productService.findHots();
        req.setAttribute("news", list01);
        req.setAttribute("hots", list02);
        //转发到真实的首页
        return "/jsp/index.jsp";
    }
}
