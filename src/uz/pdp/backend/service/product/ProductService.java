package uz.pdp.backend.service.productService;

import uz.pdp.backend.model.product.Product;
import uz.pdp.backend.role.ProductType;
import uz.pdp.backend.service.BaseService;
import uz.pdp.backend.service.support.Remove;
import uz.pdp.backend.service.support.Search;

import java.util.ArrayList;
import java.util.List;

public interface ProductService extends BaseService<Product>, Search<Product>, Remove {
    boolean check(String name);
    String update(String name, Integer price, ProductType type, int chose, String id);
    List<Product> show(ProductType type);
    String discount(String id, double sale);

    String deleteDiscount(String productId);

}
