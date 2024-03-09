# Online shop🛒
Proyektni user va adminlar uchun productlar orasida amallarni bajaradi.
### Funksiyalari
- **Product catalog**: Foydalanuvchi mahsulotlar katoligini ko'rish,qidirish va tanlash
- **Cart**: User mahsulotlarni qo'shishi, ko'rishi, olib tashlashi va sotib olishi mumkin
- **Archive**: User sotib olgan barcha tavarlar arxivda saqlanadi
- **Delete account**: Foydalanuvchi accounti o'chirishi mumkin
- **Block/UnBlock**: Admin userlarni blocklash va blockdan ochishi mumkin
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
### Dasturni ishlatish
- **1** Main orqali run berish yoki (ctrl+shift+fn+F10) orqali run bosish kerak
- **2** Console orqali Sign In Sign Up Menularini tanlash
- **3** Sign In qilingandan so'ng Admin menu yoki User menu ochiladi