package uz.pdp.backend.service.carService;

import uz.pdp.backend.model.cart.Cart;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface CartService extends BaseService<Cart>{
    Cart getUnpaidUserCart(String userId);
    Cart get(String userId);
    List<Cart> getAllPaid(String userId);
}
