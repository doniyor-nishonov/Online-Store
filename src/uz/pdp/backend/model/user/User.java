package backend.model.user;

import backend.model.BaseModel;
import backend.role.Role;

/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 15:12
 **/
public class User extends BaseModel {
    private String userName;
    private String password;
    private Integer balance;
    private Role role;

    public User(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.balance = 100_000;
        this.role = role;
    }

    public User() {
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return
                "UserName = " + userName + "\n" +
                        "Password = " + password + "\n" +
                        "Balance = " + balance + " UZS\n" +
                        "Role = " + role
                ;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
