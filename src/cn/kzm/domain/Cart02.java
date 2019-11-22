package cn.kzm.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart02 {
    //个数不确定的购物项
    private double total;
    //总计/积分
    private List<CartItem> list = new ArrayList<CartItem>();

    //添加购物项到购物车
    //当用户点击了添加购物车按钮后可以将商品的id和数量传送给服务器

    public void addCartItemToCart(CartItem cartItem) {
        //将当前的购物项加入购物车之前，判断之前是否买过这类商品
  //    如果没有买过
         //如果买过，获取到原先买的CartItem
        //设置变量默认false，没有购买过商品
            boolean flag=false;
            CartItem oldCartItem=null;
            for(CartItem cartItem2:list){
                if (cartItem2.getProduct().getPid().equals(cartItem.getProduct().getPid())){
                    flag=true;
                    oldCartItem=cartItem2;
                }
            }
            if (flag=false){
                list.add(cartItem);
            }else {
               oldCartItem.setNum(oldCartItem.getNum()+cartItem.getNum());
            }

    }

    //移除购物项
    public void removeCartItem(String pid) {
        //遍历List,看每个CartItem上的product对象上的id是否和pid相同
        for (CartItem cartItem : list) {
            if (cartItem.getProduct().getPid().equals(pid)) {
                //删除当前的CartItem

            }
        }
    }

    //  清空购物车
    public void clearCart() {
        list.clear();
    }
}
