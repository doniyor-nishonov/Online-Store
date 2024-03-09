package backend.model.product;

import backend.model.BaseModel;
import backend.role.ProductType;

import java.util.UUID;

/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 15:57
 **/
public class Product extends BaseModel {
    private String name;
    private Integer price;
    private int count;
    private ProductType type;

    public Product() {
    }

    public Product(String name, Integer price, int count,ProductType type) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.type = type;
    }

    @Override
    public String toString() {
        return
                "[" + super.getId() + "] " + name + "  |  " + price + " UZS" +
                        "  |  " + count + " | " + type;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
