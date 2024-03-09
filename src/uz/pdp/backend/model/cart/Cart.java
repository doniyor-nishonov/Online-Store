package uz.pdp.backend.model.cart;

import uz.pdp.backend.model.BaseModel;


/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 21:53
 **/
public class Cart extends BaseModel {
    private String userId;
    private boolean isPaid;

    public Cart(String userId) {
        this.userId = userId;
        this.isPaid = false;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }


}
