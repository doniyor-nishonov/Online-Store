package uz.pdp.frontend;

import uz.pdp.backend.model.cart.Cart;
import uz.pdp.backend.model.order.Order;
import uz.pdp.backend.model.product.Product;
import uz.pdp.backend.role.ProductType;

import java.text.DecimalFormat;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import static uz.pdp.frontend.tools.COLOR.*;
import static uz.pdp.frontend.tools.COLOR.GREEN;
import static uz.pdp.frontend.tools.Input.checkDataForNotNull;
import static uz.pdp.frontend.tools.Input.inputInt;
import static uz.pdp.frontend.Main.*;
import static uz.pdp.frontend.ProductController.*;

public class CartController {
    public static Cart curCart;
    public static void buyProduct() {
        while (true) {
            ProductType type = getProductType();
            productList(type);
            int index = inputInt("Select") - 1;
            String productId = getProductId(type, index);
            if ("EMPTY".equals(productId)) {
                return;
            }
            int countProduct = inputInt("Count");
            curCart=getCart();
            Order order = new Order(curCart.getId(), productId, countProduct);
            boolean isWorked = orderServiceService.add(order);
            notifacationService.notificationMessage("Cart","added",isWorked);
            System.out.println("Would you like to buy again?\n" + GREEN + "1.Yes\t2.No\n" + STOP);
            int chose = inputInt("Choose");
            if (chose != 1) {
                System.out.println("Good bye👋👋👋");
                return;
            }
        }
    }

    private static Cart getCart() {
        Cart cart = cartServiceImp.getUnpaidUserCart(curUser.getId());
        if (cart == null) {
            cart = new Cart(curUser.getId());
            cartServiceImp.add(cart);
        }
        return cart;
    }


    public static void showCartProducts() {
        List<Order> list = orderServiceService.getAll(curCart);
        productListPrint(list);
    }

    public static void removeProduct() {
        showCartProducts();
        if (checkDataForNotNull(orderServiceService.getAll(cartServiceImp.getUnpaidUserCart(curUser.getId())))) {
            int index = inputInt("Choose") - 1;
            Order order = orderServiceService.getAll(curCart).get(index);
            orderServiceService.remove(curCart.getId(), order.getProductId());
        } else
            System.out.println(RED + "Data Base is Empty" + STOP);
    }

    public static void showCart() {
        showCartProducts();
        List<Order> all = orderServiceService.getAll(curCart);
        if (checkDataForNotNull(all)) {
            System.out.println(GREEN + "Do you want to buy?\n" + STOP + "[1] - Yes\t\t[0] - No");
            int chose = inputInt("Select");
            switch (chose) {
                case 1 -> {
                    System.out.println(bill(all));
                    getCart().setPaid(true);
                    notifacationService.notificationMessage("Product","bought",true);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 0 -> System.out.println("Exit✅");
                default -> System.out.println(RED + "Wrong choice❌" + STOP);
            }
        } else
            System.out.println(RED + "Data Base is Empty" + STOP);
    }

    public static String bill(List<Order> list) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringJoiner sj = new StringJoiner("\n", "----------BILL----------\n", "\n-------------------------");
        double total = 0;
        for (Order order : list) {
            Product p = productService.get(order.getProductId());
            total += p.getPrice() * order.getCount();
            sj.add(p.getName() + "\t" + order.getCount() + ":\t" + decimalFormat.format(p.getPrice()));
        }
        sj.add(GREEN + "Total-> " + STOP + decimalFormat.format(total));
        return sj.toString();
    }
    public static void clearCart(){
        List<Order> all = orderServiceService.getAll(curCart);
        if(checkDataForNotNull(all)){
            boolean clear = orderServiceService.clear(getCart());
            if (clear)
                System.out.println(GREEN+"Cleared"+STOP);

        }else
            System.out.println("Cart already cleared✅");
    }
    public static void archiveCart(){
        List<Cart> allPaid = cartServiceImp.getAllPaid(curUser.getId());
        List<Order> archive = orderServiceService.getArchive(allPaid);
        if (checkDataForNotNull(archive)) {
            productListPrint(archive);
        }
    }

    private static void productListPrint(List<Order> archive) {
        for (int i = 0; i < archive.size(); i++) {
            Product product = productService.get(archive.get(i).getProductId());
            System.out.println((i + 1) + ". " + product + " [Amount " + archive.get(i).getCount() + "]");
        }
    }
}
