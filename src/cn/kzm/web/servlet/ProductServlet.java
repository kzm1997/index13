package cn.kzm.web.servlet;

import cn.kzm.domain.PageModel;
import cn.kzm.domain.Product;
import cn.kzm.service.ProductService;
import cn.kzm.service.serviceImpl.ProductServiceImpl;
import cn.kzm.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 产品信息展示
 */
@WebServlet(name = "ProductServlet",
        urlPatterns = {"/ProductServlet"})
public class ProductServlet extends BaseServlet {
    public String findProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取pid
        String pid = req.getParameter("pid");
        //根据商品pid获取商品信息
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProductBypid(pid);
        req.setAttribute("product", product);
        return "/jsp/product_info.jsp";
    }

    public String findProductBycidWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取cid,num
        String cid = req.getParameter("cid");
        int curNum = Integer.parseInt(req.getParameter("num"));
        //调用业务层功能：以分页形式查询当前类别下商品信息
        ProductService productService = new ProductServiceImpl();
        PageModel pm = productService.findProductBycidWithPage(cid, curNum);
        //将pagemodel放到request
        req.setAttribute("page", pm);
        return "/jsp/product_list.jsp";
    }
}
