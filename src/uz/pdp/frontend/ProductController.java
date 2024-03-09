package uz.pdp.frontend;
import uz.pdp.backend.model.product.Product;
import uz.pdp.backend.role.ProductType;

import java.util.List;

import static uz.pdp.frontend.tools.COLOR.*;
import static uz.pdp.frontend.tools.COLOR.GREEN;
import static uz.pdp.frontend.tools.Input.inputInt;
import static uz.pdp.frontend.tools.Input.inputStr;
import static uz.pdp.frontend.tools.MENU.productType;
import static uz.pdp.frontend.tools.MENU.update;
import static uz.pdp.frontend.Main.*;

public class ProductController {
    public static void discountProduct() {
        ProductType type = getProductType();
        productList(type);
        int index = inputInt("Select")-1;
        String productId = getProductId(type, index);
        if("EMPTY".equals(productId)){
            System.out.println(RED + "Product is empty" + STOP);
            return;
        }
        double sale = inputInt("Sale");
        String hint = productService.discount(productId, sale);
        System.out.println(hint);
    }



    public static void showProduct() {
        ProductType type = getProductType();
        if (type == null) return;
        productList(type);
    }
    public static void deleteDiscount() {
        ProductType type = getProductType();
        productList(type);
        int index = inputInt("Select") - 1;
        String productId = getProductId(type, index);
        String hint = productService.deleteDiscount(productId);
        System.out.println(hint);
    }
    public static String getProductId(ProductType type, int index) {
        List<Product> products = productService.show(type);
        if (products.isEmpty())
            return "EMPTY";
        if (index < 0 || index > products.size())
            return RED + "Index of bought" + STOP;
        return products.get(index).getId();
    }

    public static void productList(ProductType type) {
        List<Product> list = productService.show(type);
        if (list.isEmpty())
            System.out.println(RED + "Shop is empty" + STOP);
        else
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i));
            }
    }

    public static ProductType getProductType() {
        System.out.println("Which one?");
        System.out.println(productType);
        int choose = inputInt("Choose");
        ProductType type = typeSwitch(choose);
        if (type == null) {
            System.out.println(RED + "Wrong option" + STOP);
            return null;
        }
        return type;
    }

    public static void deleteProduct() {
        ProductType type = getProductType();
        productList(type);
        int index = inputInt("Select") - 1;
        String productId = getProductId(type, index);
        boolean b = productService.remove(productId);
        System.out.println(b?"Deleted✔️":"Failed");
    }

    public static void updateProduct() {
        System.out.println("You are in the Update section");
        ProductType type = getProductType();
        productList(type);
        int index = inputInt("Select") - 1;
        String productId = getProductId(type, index);
        System.out.println(update);
        int chose = inputInt("Choose");
        String name = null;
        Integer price = null;
        ProductType newType = null;
        int count = 0;
        switch (chose) {
            case 1 -> name = inputStr("Name");
            case 2 -> price = inputInt("Price");
            case 3 -> count = inputInt("Count");
            case 4 -> {
                System.out.println(productType);
                int choseType = inputInt("Select");
                if (choseType < 0 || choseType > 8) {
                    System.out.println(RED + "Error" + STOP);
                    return;
                }
                newType = typeSwitch(choseType);
            }
            default -> {
                System.out.println(RED + "Wrong choice❌" + STOP);
                return;
            }
        }
        String hint = productService.update(name, price, newType, chose, productId);
        System.out.println(hint);
    }

    public static void addProduct() {
        System.out.println("Which one do you want to create?");
        System.out.println(productType);
        int chose = inputInt("Choose");
        ProductType type = typeSwitch(chose);
        if (type == null) {
            System.out.println(RED + "Wrong choose" + STOP);
            return;
        }
        String name = inputStr("Name");
        int price = 0;
        if (!productService.check(name)) {
            price = inputInt("Price");
        }
        int count = inputInt("Count");
        boolean b = productService.add(new Product(name, price, type));
        System.out.println(b?"Added✅":"Failed");
    }



    public static List<Product> searchProduct() {
        String name = inputStr("Enter name");
        return productService.search(name);
    }

    public static void search() {
        String name = inputStr("Enter name");
        List<Product> search = productService.search(name);
        if (search.isEmpty()) {
            System.out.println(RED + "Data Base is Empty" + STOP);
            return;
        }
        for (int i = 0; i < search.size(); i++) {
            Product p = search.get(i);
            System.out.println(i + 1 + ". " + p);
        }
    }

    public static void upBalance() {
        System.out.println(GREEN + "You are in a Bank branch" + STOP);
        System.out.println(CYAN + "Your Balance-> " + STOP + curUser.getBalance() + " UZS");
        double newBalance = inputInt("Enter balance");
        userService.addBalance(curUser.getId(), newBalance);
        System.out.println(GREEN + "The balance was successfully replenished" + STOP);
    }




    public static ProductType typeSwitch(int choose) {
        ProductType type;
        switch (choose) {
            case 1 -> type = ProductType.VEGETABLE;
            case 2 -> type = ProductType.FRUIT;
            case 3 -> type = ProductType.BREAD;
            case 4 -> type = ProductType.DRINK;
            case 5 -> type = ProductType.TECHNIQUE;
            case 6 -> type = ProductType.SWEET;
            case 7 -> type = ProductType.PERFUMERY;
            default -> type = null;
        }
        return type;
    }
}
