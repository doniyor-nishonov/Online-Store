package uz.pdp.backend.model.order;

import uz.pdp.backend.model.BaseModel;

public class Order extends BaseModel {
    private String cartId;
    private String productId;
    private int count;

    public Order(String cartId, String productId, int count) {
        this.cartId = cartId;
        this.productId = productId;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCartId() {
        return cartId;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
