package web.service;

import web.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    List<User> getAllUsers ();

    void addUser(User user);

    void removeUser(int id);

    void updateUser(User user);
    Object getUserById(int id);
}
