package cn.kzm.web.servlet;

import cn.kzm.domain.Cart;
import cn.kzm.domain.CartItem;
import cn.kzm.domain.Product;
import cn.kzm.service.ProductService;
import cn.kzm.service.serviceImpl.ProductServiceImpl;
import cn.kzm.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends BaseServlet {
    //添加购物项到购物车
    public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //从session获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart) {
       //如果获取不到，创建购物车对象，放在session中
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        //获取到，使用它
        //获取上商品id和数量
        String pid = req.getParameter("pid");
        int num = Integer.parseInt(req.getParameter("quantity"));
        //通过商品Id查询商品数量
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProductBypid(pid);
        //获取到待购买的购物项
        CartItem cartItem = new CartItem();
        cartItem.setNum(num);
        cartItem.setProduct(product);
        //调用购物车上的方法
        cart.addCartItemToCar(cartItem);
        //重定向到/jsp/cart.jsp
        resp.sendRedirect("/index11/jsp/cart.jsp");

        return null;

    }

    //删除购物项
    public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取待删除商品pid
        String pid = req.getParameter("pid");
        //获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //调用购物车删除购物项方法
        cart.removeCartItem(pid);
        //重定向到/jsp/cart.jsp
        resp.sendRedirect("/index11/jsp/cart.jsp");
        return null;
    }

    public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clearCart();
        resp.sendRedirect("/index11/jsp/cart.jsp");
        return null;
    }

}
