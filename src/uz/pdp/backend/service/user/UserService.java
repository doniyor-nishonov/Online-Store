package uz.pdp.backend.service.user;

import uz.pdp.backend.model.user.User;
import uz.pdp.backend.service.BaseService;
import uz.pdp.backend.service.support.GetAll;

public interface UserService extends BaseService<User>, GetAll<User> {
    User login(String userName, String password);
    void addBalance(String id, double newBalance);

    void deleteAccount(String id);

    String BlockUser(String id);

    String unBlockUser(String id);
}
