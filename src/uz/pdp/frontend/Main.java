package uz.pdp.frontend;

import static uz.pdp.frontend.tools.COLOR.*;
import static uz.pdp.frontend.tools.Input.*;
import static uz.pdp.frontend.tools.MENU.*;
import static uz.pdp.frontend.UserController.*;

import uz.pdp.backend.service.cart.CartService;
import uz.pdp.backend.service.cart.CartServiceImp;
import uz.pdp.backend.service.notification.NotifacationService;
import uz.pdp.backend.service.notification.NotificationServiceImp;
import uz.pdp.backend.service.order.OrderService;
import uz.pdp.backend.service.order.OrderServiceImp;
import uz.pdp.backend.service.product.ProductService;
import uz.pdp.backend.service.product.ProductServiceImp;
import uz.pdp.backend.model.user.User;
import uz.pdp.backend.service.user.UserService;
import uz.pdp.backend.service.user.UserServiceImp;

/**
 * @author : Doniyor Nishonov
 * @project : Default (Template) Project
 * @date :  2024-02-10-> 14:36
 **/
public class Main {
    public static UserServiceImp userService = new UserServiceImp();
    public static ProductServiceImp productService = new ProductServiceImp();
    public static User curUser;
    public static OrderService orderServiceService = new OrderServiceImp();
    public static CartService cartServiceImp = new CartServiceImp();
    public static NotifacationService notifacationService = new NotificationServiceImp();

    public static void main(String[] args) {

        System.out.println(BACK_BLACK + BLUE + ITALIC + "\u001B[52m  Welcome to shops  " + STOP);
        while (true) {
            System.out.println(menu);
            int choose = inputInt("Choose");
            switch (choose) {
                case 1 -> login();
                case 2 -> signUp();
                case 0 -> {
                    System.out.println(YELLOW + "\u001B[52m  Bye bye👋👋👋  " + STOP);
                    System.exit(0);
                }
                default -> System.out.println(RED + "Wrong choice❌" + STOP);
            }
        }
    }
}