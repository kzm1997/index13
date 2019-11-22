package cn.kzm.web.servlet;

import cn.kzm.domain.Category;
import cn.kzm.service.CategoryService;
import cn.kzm.service.serviceImpl.CategoryServiceImpl;
import cn.kzm.utils.UUIDUtils;
import cn.kzm.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet",urlPatterns = {"/AdminCategoryServlet"})
public class AdminCategoryServlet extends BaseServlet {
    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取全部分类信息，放入request
        CategoryService categoryService=new CategoryServiceImpl();
        List<Category>list=categoryService.getAllCats();
        req.setAttribute("allCats",list);
        //转发到/admin/category/list.jsp
        return "/admin/category/list.jsp";
    }
    public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return  "/admin/category/add.jsp";
    }

    public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取分类名称
        String cname=req.getParameter("cname");
        //创建分类ID
        String id=UUIDUtils.getId();
        Category category=new Category();
        category.setCname(cname);
        category.setCid(id);
        //调用业务层添加分类功能
        CategoryService categoryService =new CategoryServiceImpl();
        categoryService.addCategory(category);
        //重定向到查询全部分类信息
        resp.sendRedirect("/index11/AdminCategoryServlet?method=findAllCats");
        return  "/admin/category/add.jsp";
    }

}
