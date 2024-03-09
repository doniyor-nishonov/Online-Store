package uz.pdp.backend.service.userService;

import uz.pdp.backend.model.user.User;
import uz.pdp.backend.service.BaseService;
import uz.pdp.backend.service.support.GetAll;
import uz.pdp.backend.service.support.Search;

public interface UserService extends BaseService<User>, GetAll<User> {
    User login(String userName, String password);
    void addBalance(String id, double newBalance);

    void deleteAccount(String id);

    String BlockUser(String id);

    String unBlockUser(String id);
}
