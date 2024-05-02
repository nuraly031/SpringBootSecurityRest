package ru.itmentor.Task_314.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itmentor.Task_314.model.Role;
import ru.itmentor.Task_314.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    void updateUser(int id, User user);

    void removeUser(int id);

    User getUserById(int id);

    List<User> listUser();

    List<Role> listRole();
}
