package frontend;

import backend.role.ProductType;

import static backend.tools.COLOR.*;
import static backend.tools.Input.*;
import static backend.tools.MENU.*;

import backend.model.product.Product;
import backend.service.buyService.BuyServiceImp;
import backend.service.productService.ProductServiceImp;
import backend.role.Role;
import backend.model.user.User;
import backend.service.userService.UserServiceImp;

import java.util.ArrayList;

/**
 * @author : Doniyor Nishonov
 * @project : Default (Template) Project
 * @date :  2024-02-10-> 14:36
 **/
public class Main {
    private static UserServiceImp userServiceImp = new UserServiceImp();
    private static ProductServiceImp productServiceImp = new ProductServiceImp();
    private static User curUser;
    private static Product product;
    private static BuyServiceImp buyServiceImp = new BuyServiceImp();

    public static void main(String[] args) {
        System.out.println(BACK_BLACK + BLUE + "\u001B[52m  Welcome to shops  " + STOP);
        while (true) {
            System.out.println(menu);
            int choose = inputInt("Choose");
            switch (choose) {
                case 1 -> login();
                case 2 -> signUp();
                case 0 -> {
                    System.out.println("\u001B[52m  Bye bye👋👋👋  " + STOP);
                    System.exit(0);
                }
                default -> System.out.println(RED + "Wrong choice❌" + STOP);
            }
        }
    }

    private static void signUp() {
        String userName = inputStr("User Name");
        String password = inputStr("Password");
        String hint = userServiceImp.signUp(userName, password);
        System.out.println(hint);
    }

    private static void login() {
        String userName = inputStr("User Name");
        String password = inputStr("Password");
        User user = userServiceImp.login(userName, password);
        if (user == null) {
            System.out.println(RED + "User not found" + STOP);
        } else if (user.getRole().equals(Role.VENDOR)) {
            vendorProfile();
        } else if (user.getRole().equals(Role.USER)) {
            curUser = user;
            userProfile();
        }
    }

    private static void userProfile() {
        App:
        while (true) {
            System.out.println(userMenu);
            int chose = inputInt("Select");
            switch (chose) {
                case 1 -> buyProduct();
                case 2 -> showBuy();
                case 3 -> showProduct();
                case 4 -> search();
                case 5 -> upBalance();
                case 6 -> archiveBuyProducts();
                case 0 -> {
                    break App;
                }
                default -> System.out.println(RED + "Wrong choice❌" + STOP);
            }
        }

    }

    private static void archiveBuyProducts() {
        ArrayList<Product> products = buyServiceImp.getArchive(curUser.getId());
        if (products.isEmpty()) {
            System.out.println(RED + "Data Base is Empty" + STOP);
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println(i+1 + ". " + p.getName() + " | " + p.getPrice() + " | " + p.getCount());
        }
        System.out.println("Would you like to see the check?\n1.Yes\t2.No");
        int chose = inputInt("Choose");
        if(chose==1){
            String checkBalance = buyServiceImp.getCheckBalance(products, buyServiceImp.balanceArchive(curUser.getId()));
            System.out.println(checkBalance);
        } else
            System.out.println("Good Bye👋");
    }

    private static ArrayList<Product> searchProduct() {
        String name = inputStr("Enter name");
        return productServiceImp.search(name);
    }
    private static void search() {
        String name = inputStr("Enter name");
        ArrayList<Product> search = productServiceImp.search(name);
        if (search.isEmpty())
            System.out.println("No such information found😥");
        else
            search.forEach(System.out::println);
    }

    private static void upBalance() {
        System.out.println(GREEN + "You are in a Bank branch" + STOP);
        System.out.println(CYAN + "Your Balance-> " + STOP + curUser.getBalance() + " UZS");
        int newBalance = inputInt("Enter balance");
        userServiceImp.addBalance(curUser.getId(), newBalance);
        System.out.println(GREEN + "The balance was successfully replenished" + STOP);
    }

    private static void showBuy() {
        ArrayList<Product> listProduct = buyServiceImp.show(curUser.getId());
        Integer balance = buyServiceImp.balance(curUser.getId());
        if (listProduct.isEmpty()) {
            System.out.println(RED + "Data Base is Empty" + STOP);
            return;
        }
        for (int i = 0; i < listProduct.size(); i++) {
            Product p = listProduct.get(i);
            System.out.println(i+1 + ". " + p.getName() + " | " + p.getPrice() + " | " + p.getCount());
        }
        System.out.println(BLUE + "Do you want to buy?\u001B[0m\n\u001B[32m1.Yes\n2.No" + STOP);
        int chose = inputInt("Select");
        if (chose == 1) {
            if (curUser.getBalance() > balance) {
                curUser.setBalance(curUser.getBalance()-balance);
                String checkBalance = buyServiceImp.getCheckBalance(listProduct, balance);
                System.out.println(checkBalance);
                buyServiceImp.addArchive(curUser.getId());
            } else {
                System.out.println(RED + "There are not enough funds in the balance" + STOP);
                System.out.println(GREEN + "Please top up the balance first" + STOP);
            }
        } else if (chose == 2) {
            System.out.println(GREEN + "Thank you for using our services" + STOP);
        } else {
            System.out.println(RED + "Wrong choice❌, Please try again" + STOP);
        }
    }

    private static void buyProduct() {
        while (true) {
            ArrayList<Product> products = searchProduct();
            if (products.isEmpty()){
                System.out.println("No such information found😥,Please try again");
                buyProduct();
            }
            products.forEach(System.out::println);
            String idProduct = inputStr("ID product");
            Product getProduct = productServiceImp.getProducts(idProduct);
            if (getProduct == null) {
                System.out.println(RED + "Error" + STOP);
                return;
            }
            String hint = buyServiceImp.add(getProduct, curUser.getId());
            System.out.println(hint);
            System.out.println("Would you like to buy again?\n" + GREEN + "1.Yes\t2.No\n" + STOP);
            int chose = inputInt("Choose");
            if(chose!=1){
                System.out.println("Good bye👋👋👋");
                return;
            }
        }

    }

    private static void vendorProfile() {
        App:
        while (true) {
            System.out.println("\n" + vendorMenu);
            int choose = inputInt("Choose");
            switch (choose) {
                case 1 -> addProduct();
                case 2 -> updateProduct();
                case 3 -> deleteProduct();
                case 4 -> showProduct();
                case 5 -> showUser();
                case 6 -> search();
                case 0 -> {
                    break App;
                }
                default -> System.out.println(RED + "Wrong choice❌" + STOP);
            }
        }
    }

    private static void showUser() {
        System.out.println(userServiceImp.show());
        int ind = inputInt("Select") - 1;
        if (ind >= 0 && ind < userServiceImp.getSize()) {
            System.out.println(userServiceImp.getUsers(ind));
        } else {
            System.out.println(RED + "Index bought exception❌" + STOP);
        }
    }

    private static void showProduct() {
        System.out.println("Which one?");
        System.out.println(productType);
        int choose = inputInt("Choose");
        ProductType type = typeSwitch(choose);
        if (type == null) {
            System.out.println(RED + "Wrong option" + STOP);
            return;
        }
        ArrayList<Product> list = productServiceImp.show(type);
        if (list.isEmpty())
            System.out.println(RED + "Shop is empty" + STOP);
        list.forEach(System.out::println);
    }

    private static void deleteProduct() {
        showProduct();
        String id = inputStr("ID product");
        String hint = productServiceImp.delete(id);
        System.out.println(hint);
    }

    private static void updateProduct() {
        System.out.println("You are in the Update section");
        showProduct();
        String id = inputStr("ID product");
        System.out.println(update);
        int chose = inputInt("Choose");
        String name = null;
        Integer price = null;
        ProductType type = null;
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
                type = typeSwitch(choseType);
            }
            default -> {
                System.out.println(RED + "Wrong choice❌" + STOP);
                return;
            }
        }
        String hint = productServiceImp.update(name, price, count, type, chose, id);
        System.out.println(hint);
    }

    private static void addProduct() {
        System.out.println("Which one do you want to add?");
        System.out.println(productType);
        int chose = inputInt("Choose");
        String name = inputStr("Name");
        Integer price = null;
        if (!productServiceImp.check(name)) {
            price = inputInt("Price");
        }
        int count = inputInt("Count");
        String hint = productServiceImp.add(new Product(name, price, count, typeSwitch(chose)));
        System.out.println(hint);
    }

    private static ProductType typeSwitch(int choose) {
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