package uz.pdp.backend.service.order;

import uz.pdp.backend.model.cart.Cart;
import uz.pdp.backend.model.order.Order;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface OrderService extends BaseService<Order>{
    List<Order> getAll(Cart cart);
    List<Order> getArchive(List<Cart> cart);
    boolean remove(String cartId,String productId);
    boolean clear(Cart cart);

}
