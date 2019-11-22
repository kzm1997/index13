package cn.kzm.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    //总计
    private double total;
    //个数不确定的购物项pid<==>CartItem
    Map<String, CartItem> map = new HashMap<String, CartItem>();


    //添加购物项到购物车
    public void addCartItemToCar(CartItem cartItem) {
        String pid = cartItem.getProduct().getPid();
        if (map.containsKey(pid)) {
            CartItem oldItem = map.get(pid);
            oldItem.setNum(oldItem.getNum() + cartItem.getNum());

        } else {
            map.put(pid, cartItem);
        }
    }

    //判断购物车是否为空 返回map中所有的值
    public Collection<CartItem> getCartItems() {
        return map.values();
    }

    //移除购物项
    public void removeCartItem(String pid) {
        map.remove(pid);
    }

    //清空购物车
    public void clearCart() {
        map.clear();
    }

    public double getTotal() {
        total = 0;
        Collection<CartItem> values = map.values();
        for (CartItem cartItem : values) {
            total += cartItem.getSubTotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

}
