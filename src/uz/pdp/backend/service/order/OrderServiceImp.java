package uz.pdp.backend.service.order;

import uz.pdp.backend.model.cart.Cart;
import uz.pdp.backend.model.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderServiceImp implements OrderService {
    private ArrayList<uz.pdp.backend.model.order.Order> orderList;

    public OrderServiceImp() {
        this.orderList = new ArrayList<>();
    }

    @Override
    public boolean add(Order order) {
        for (Order o : orderList) {
            if(Objects.equals(o.getCartId(),order.getCartId())&&
                    Objects.equals(o.getProductId(),order.getProductId())) {
                o.setCount(o.getCount() + order.getCount());
                return true;
            }
        }
        orderList.add(order);
        return true;
    }
    @Override
    public boolean remove(String cartId, String productId) {
        return orderList.removeIf(order -> Objects.equals(order.getCartId(), cartId) && Objects.equals(order.getProductId(), productId));
    }


    @Override
    public List<Order> getAll(Cart cart) {
        return orderList.stream()
                .filter((order)->order.getCartId().equals(cart.getId())&& cart.getPaid())
                .collect(Collectors.toList());
    }

    @Override
    public boolean clear(Cart cart) {
        if(cart.getPaid())
            return false;
        orderList.removeIf(order -> Objects.equals(cart.getId(), order.getCartId()));
        return true;
    }

    @Override
    public List<Order> getArchive(List<Cart> carts) {
        ArrayList<Order> archiveList = new ArrayList<>();
        for (Cart cart : carts) {
            for (Order order : orderList) {
                if (cart.getPaid()&&Objects.equals(order.getCartId(),cart.getId())) {
                    updateOrAddToArchiveList(order, archiveList);
                }
            }
        }
        return archiveList;
    }

    private void updateOrAddToArchiveList(Order order, ArrayList<Order> archiveList) {
        boolean isNotWorked = true;
        for (Order o : archiveList) {
            if(o.getProductId().equals(order.getProductId())){
                o.setCount(o.getCount()+ order.getCount());
                isNotWorked = false;
                break;
            }
        }
        if (isNotWorked)
            archiveList.add(order);
    }
}