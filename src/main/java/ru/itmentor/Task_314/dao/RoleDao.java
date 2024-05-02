package ru.itmentor.Task_314.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.Task_314.model.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
