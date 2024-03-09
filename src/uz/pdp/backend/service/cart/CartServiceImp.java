package uz.pdp.backend.service.cart;

import uz.pdp.backend.model.cart.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 21:45
 **/
public class CartServiceImp implements CartService {
    private ArrayList<Cart> cartList;
    public CartServiceImp() {
        this.cartList = new ArrayList<>();
    }
    public Cart getBuys(int ind) {
        return cartList.get(ind);
    }

    public void setBuys(Cart cart) {
        this.cartList.add(cart);
    }

    @Override
    public boolean add(Cart cart) {
        cartList.add(cart);
        return true;
    }

    public Cart getUnpaidUserCart(String userId){
        Optional<Cart> first = cartList.stream()
                .filter(c -> !c.getPaid() && Objects.equals(c.getUserId(), userId))
                .findFirst();
        return first.orElseGet(()->{
            Cart cart = new Cart(userId);
            add(cart);
            return cart;
        });
    }

    @Override
    public Cart get(String userId) {
        return cartList.stream()
                .filter((cart ->cart.getUserId().equals(userId) && cart.getPaid()))
                .findFirst().orElse(null);
    }

    @Override
    public List<Cart> getAllPaid(String userId) {
        return cartList.stream()
                .filter((cart -> cart.getUserId().equals(userId)))
                .collect(Collectors.toList());
    }
}
