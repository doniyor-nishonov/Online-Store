package uz.pdp.backend.model.product;

import uz.pdp.backend.model.BaseModel;
import uz.pdp.backend.role.ProductType;

/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 15:57
 **/
public class Product extends BaseModel {
    private String name;
    private double price;
    private ProductType type;
    private double discount;

    public Product(String name, double price, ProductType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        if(discount==0)
            return
                name + "  |  " + price + " UZS"  + " | " + type;
        return name + " | " + price + " UZS /"+"Sale " + discount + " UZS" + " | " + type;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
