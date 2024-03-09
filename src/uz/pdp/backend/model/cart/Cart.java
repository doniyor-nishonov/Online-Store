package uz.pdp.backend.model.buy;

import uz.pdp.backend.model.product.Product;

/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 21:53
 **/
public class Buy {
    private String id;
    private Product product;
    private boolean Active;


    public Buy(String id, Product product) {
        this.id = id;
        this.product = product;
        this.Active = true;
    }

    public Product getProduct() {
        return product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    @Override
    public String toString() {
        return  product.getName() + "\t | " +
                product.getPrice() + "\t | " +
                product.getCount();
    }
}
