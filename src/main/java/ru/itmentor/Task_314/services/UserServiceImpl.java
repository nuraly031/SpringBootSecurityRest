package ru.itmentor.Task_314.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.Task_314.dao.RoleDao;
import ru.itmentor.Task_314.dao.UserDao;
import ru.itmentor.Task_314.model.Role;
import ru.itmentor.Task_314.model.User;
import ru.itmentor.Task_314.util.UserNotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        User us = userDao.getById(id);
        us.setUserName(user.getUserName());
        us.setUserLastname(user.getUserLastname());
        us.setUserMail(user.getUserMail());
        us.setUserAge(user.getUserAge());
        us.setPassword(user.getPassword());
        us.setRoles(user.getRoles());
        userDao.save(us);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUserById(int id) {
        Optional<User> founduser = userDao.findById(id);
        return founduser.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> listUser() {
        return userDao.findAll();
    }

    @Override
    public List<Role> listRole() {
        return roleDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByuserMail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not found user !");
        }
        return user.get();
    }
}
