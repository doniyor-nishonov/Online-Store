package uz.pdp.frontend;

import uz.pdp.backend.model.user.User;
import uz.pdp.backend.role.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uz.pdp.frontend.tools.COLOR.*;
import static uz.pdp.frontend.tools.Input.inputInt;
import static uz.pdp.frontend.tools.Input.inputStr;
import static uz.pdp.frontend.tools.MENU.userMenu;
import static uz.pdp.frontend.tools.MENU.vendorMenu;
import static uz.pdp.frontend.CartController.*;
import static uz.pdp.frontend.Main.*;
import static uz.pdp.frontend.ProductController.*;
import static uz.pdp.frontend.ProductController.search;

public class UserController {
    public static void userProfile() {

        App:
        while (true) {
            System.out.println(userMenu);
            int chose = inputInt("Select");
            switch (chose) {
                case 1 -> buyProduct();
                case 2 -> showCart();
                case 3 -> removeProduct();
                case 4 -> showProduct();
                case 5 -> search();
                case 6 -> clearCart();
                case 7 -> upBalance();
                case 8 -> archiveCart();
                case 9 -> {
                    deleteAccount();
                    return;
                }
                case 0 -> {
                    break App;
                }
                default -> System.out.println(RED + "Wrong choice❌" + STOP);
            }
        }

    }

    public static void vendorProfile() {
        App:
        while (true) {
            System.out.println("\n" + vendorMenu);
            int choose = inputInt("Choose");
            switch (choose) {
                case 1 -> addProduct();
                case 2 -> updateProduct();
                case 3 -> deleteProduct();
                case 4 -> discountProduct();
                case 5 -> deleteDiscount();
                case 6 -> showProduct();
                case 7 -> showUser();
                case 8 -> search();
                case 9 -> blockUser();
                case 10 -> unBlockUser();
                case 0 -> {
                    break App;
                }
                default -> System.out.println(RED + "Wrong choice❌" + STOP);
            }
        }
    }

    public static void signUp() {
        String userName = inputStr("User Name");
        String password = inputStr("Password");
        boolean test  = userService.add(new User(userName,password,Role.USER));
    }

    public static void login() {
        String userName = inputStr("User Name");
        String password = inputStr("Password");
        curUser = userService.login(userName, password);
        if (curUser == null) {
            System.out.println(RED + "User not found" + STOP);
        } else if (curUser.getRole().equals(Role.VENDOR)) {
            vendorProfile();
        } else if (curUser.getRole().equals(Role.USER)) {
            userProfile();
        } else if (curUser.getRole().equals(Role.BLOCK)) {
            System.out.println(RED + "User blocked " + STOP);
        }
    }

    public static void showUser() {
        List<User> show = userService.getAll();
        for (int i = 0; i < show.size(); i++) {
            User user = show.get(i);
            System.out.println(i + 1 + ". " + user.getUserName() + " | " + user.getRole());
        }
        int index = inputInt("Choose") - 1;
        if (index > 0 && index < show.size())
            System.out.println(show.get(index));
        else
            System.out.println(RED + "Wrong number of choices for " + STOP);
    }

    public static String getUserID(int index) {
        if (index < 0 || index >= userService.getSize())
            return null;
        return userService.getUsers(index).getId();
    }

    public static void deleteAccount() {
        System.out.println("Are you sure you want to remove your account?");
        System.out.println("1.Yes\t2.No");
        int chose = inputInt("Choose");
        if (chose == 1) {
            String password = inputStr("Password");
            String again = inputStr("Again");
            if (again.equals(password)) {
                userService.deleteAccount(curUser.getId());
                System.out.println(GREEN + "Account deleted" + STOP);
            } else {
                System.out.println(RED + "Wrong password❌" + STOP);
            }
        } else
            System.out.println("Good Bye��");
    }

    public static void unBlockUser() {
        showUser();
        int index = inputInt("UnbBlock user") - 1;
        String id = getUserID(index);
        if (Objects.equals(id, null)) {
            System.out.println(RED + "Wrong choice❌" + STOP);
            return;
        }
        String hint = userService.unBlockUser(id);
        System.out.println(hint);
    }

    public static void blockUser() {
        showUser();
        int index = inputInt("Block user") - 1;
        String id = getUserID(index);
        if (Objects.equals(id, null)) {
            System.out.println(RED + "Wrong choice��" + STOP);
            return;
        }
        String hint = userService.BlockUser(id);
        System.out.println(hint);
    }
}
