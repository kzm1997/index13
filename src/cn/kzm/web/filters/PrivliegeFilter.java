package cn.kzm.web.filters;

import cn.kzm.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "PrivliegeFilter",
urlPatterns = {"/jsp/cart.jsp",
"/jsp/order_info.jsp",
"/jsp/order_list.jsp"})
public class PrivliegeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest myReq=(HttpServletRequest)req;
        User user=(User) myReq.getSession().getAttribute("loginUser");
        if (null!=user){
            //如果存在放行
            chain.doFilter(req, resp);
        }else {
            //如果不存在，转到提示页面
            myReq.setAttribute("msg","请用户登录");
            myReq.getRequestDispatcher("/jsp/info.jsp").forward(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
