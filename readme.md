# Online shop🛒
This project is an online shop implementation with basic features to manage products, customers, and orders. It is developed using [programming language] and [web framework].
### Features
- ***Product Management***: 
  -     Add, edit, and delete products.
        Display product details, including name, price, and description.
- ***Customer Management***: 
  -     Maintain a customer database.
        View customer information.
- ***Order Processing***: 
  -     Place and process customer orders.
        Calculate total order amount.
- ***User Authentication***: 
  -     Secure user accounts.
        Access control for admin and customer roles.
- ***Responsive Design***: 
  -     Ensure a user-friendly experience on various devices.
```java
public static void login() {
        String userName = inputStr("User Name");
        String password = inputStr("Password");
        curUser = userServiceImp.login(userName, password);
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
```
## Installation
### 1.Clone Repository:
-       git clone https://github.com/doniyor20054/Online-Store
### 2.Navigate to Project Directory:
-       cd Online-Store
### 3.Open Intellij IDEA:
-       open Online Store Project
### 4.Run Intellij IDEA:
-       (ctrl+shift+F10) or Run button

## Usage
### Admin Panel:
-       Access the admin panel by navigating to /admin.
        Login with admin credentials.
### Customer Interface:
-       Customers can browse products, add them to the cart, and place orders.

## Contributing
- **Contributions are welcome! Please follow these guidelines**:
  -     Fork the repository.
        Create a new branch for your feature or bug fix.
        Commit your changes.
        Submit a pull request.
## License
    This project is licensed under the MIT License.