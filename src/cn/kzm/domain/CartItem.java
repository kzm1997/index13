package cn.kzm.domain;

public class CartItem {
    private Product product; //携带购物项的3种参数（图片路径，商品名称，商品价格）
    private int num;  //当前类别商品数量
    private double subTotal; //当前商品价格小计


    public double getSubTotal(){
        return product.getShop_price()*num;

    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public CartItem(Product product, int num, double subTotal) {
        this.product = product;
        this.num = num;
        this.subTotal = subTotal;
    }

    public CartItem() {
    }
}
