package uz.pdp.backend.tools;

import static uz.pdp.backend.tools.COLOR.*;

/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 17:23
 **/
public interface MENU {
    String vendorMenu = CYAN + """
                Menu:
                1. Add Product
                2. Update Product
                3. Delete Product
                4. Discount Product
                5. Delete Discount
                6. Show Product List
                7. Show User List
                8. Search Product
                9. Block User
                10.Unblock User
                0. Exit
                """ + STOP;
    String menu = CYAN + """
                Menu:
                1. Login
                2. Sign Up
                0. Exit
                """ + STOP;
    String userMenu = CYAN + """
                Menu:
                1. Buy Product
                2. Show Cart
                3. Clear Cart 
                4. Show Product List
                5. Search Product
                6. Top up the balance
                7. Archive Cart 
                8. Delete Account
                0. Exit
                """ + STOP;
    String update = CYAN + """
            1. Name
            2. Price
            3. Count
            4. Type
            """ + STOP;
    String productType = CYAN + """
            1. VEGETABLE
            2. FRUIT
            3. BREAD
            4. DRINK
            5. PHONE
            6. SWEET
            7. PERFUMERY
            """ + STOP;
}
