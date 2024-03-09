package uz.pdp.backend.service.userService;

import uz.pdp.frontend.tools.COLOR;
import uz.pdp.backend.role.Role;
import uz.pdp.backend.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 16:31
 **/
public class UserServiceImp implements UserService {
    private ArrayList<User> users;

    public UserServiceImp() {
        this.users = new ArrayList<>();
        users.add(new User("d", "d", Role.VENDOR));
        users.add(new User("m", "m", Role.USER));
        users.add(new User("jalol", "1234", Role.USER));
        users.add(new User("said", "1234", Role.USER));
        users.add(new User("sarchik", "1234", Role.USER));
        users.add(new User("sancho", "1234", Role.USER));
    }


    public int getSize() {
        return users.size();
    }

    public User getUsers(int ind) {
        return users.get(ind);
    }

    public void setUsers(User user) {
        this.users.add(user);
    }

    @Override
    public boolean add(User newUser) {
        boolean userNameExists = users.stream()
                .anyMatch(user -> user.getUserName().equals(newUser.getUserName()));

        if (userNameExists) {
            return false;
        }

        setUsers(newUser);
        return true;
    }

    @Override
    public User login(String userName, String password) {
        return users.stream()
                .filter(user -> user.getUserName().equals(userName) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }


    @Override
    public ArrayList<User> getAll() {
        return users;
    }

    @Override
    public void addBalance(String id, double newBalance) {
        users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .ifPresent(user -> user.setBalance(user.getBalance() + newBalance));
    }


    @Override
    public void deleteAccount(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }


    @Override
    public String BlockUser(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                if (user.getRole().equals(Role.VENDOR))
                    return COLOR.RED + "You can't block vendor" + COLOR.STOP;
                user.setRole(Role.BLOCK);
                return COLOR.GREEN + "User successfully blocked" + COLOR.STOP;
            }
        }
        return COLOR.RED + "User not found" + COLOR.STOP;
    }

    @Override
    public String unBlockUser(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                if (user.getRole().equals(Role.VENDOR) || user.getRole().equals(Role.USER))
                    return COLOR.RED + "Already unblock✔" + COLOR.STOP;
                user.setRole(Role.USER);
                return COLOR.GREEN + "User successfully unblocked" + COLOR.STOP;
            }
        }
        return COLOR.RED + "User not found" + COLOR.STOP;
    }
}
