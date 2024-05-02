package ru.itmentor.Task_314.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.Task_314.model.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByuserName(String name);
    Optional<User> findByuserMail(String name);

}
