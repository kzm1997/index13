package cn.kzm.web.servlet;

import cn.kzm.domain.User;
import cn.kzm.service.UserService;
import cn.kzm.service.serviceImpl.UserServiceImpl;
import cn.kzm.utils.MailUtils;
import cn.kzm.utils.MyBeanUtils;
import cn.kzm.utils.UUIDUtils;
import cn.kzm.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "UserServlet",
        urlPatterns = {"/UserServlet"}
)
public class UserServlet extends BaseServlet {
    public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "jsp/register.jsp";
    }

    public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        user.setUid(UUIDUtils.getId());
        user.setCode(UUIDUtils.getCode());
        user.setState(0);
        MyBeanUtils.populate(user, map);//将map的属性赋值给user
        //调用业务层注册功能
        UserService userService = new UserServiceImpl();
        try {
            //注册成功，向用户发送邮件，跳转到提示页面
            userService.userReiget(user); //向服务器添加注册信息
            //发送邮件
            MailUtils.sendMail(user.getEmail(), user.getCode());
            request.setAttribute("msg", "用户注册成功，请激活");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "用户注册失败，请重新注册");
        }
        return "/jsp/info.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //获取激活码
        String code = request.getParameter("code");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.userActive(code);
        if (flag == true) {
            request.setAttribute("msg", "用户激活成功，请登录");
            return "/jsp/login.jsp";
        } else {
            //激活失败
            request.setAttribute("msg", "激活失败，请重新激活！");
            return "/jsp/info.jsp";
        }

    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "jsp/login.jsp";
    }

    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        MyBeanUtils.populate(user, request.getParameterMap());
        UserService userService = new UserServiceImpl();
        User user2 = null; //可以携带用户所有数据
        try {
            user2 = userService.userLogin(user);
            request.getSession().setAttribute("loginUser", user2);
            response.sendRedirect("/index11/index.jsp");
            return null;
            //登录成功，将用户信息传入session


        } catch (Exception e) {
            //用户登录失败
            String msg = e.getMessage();
            request.setAttribute("msg", msg);
            return "/jsp/login.jsp";

        }
    }

    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清除session
        request.getSession().invalidate();
        //重定向到首页
        response.sendRedirect("/index11/index.jsp");
        return null;
    }

}
