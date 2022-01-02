package com.example.deliveryApp.security.repo;

import com.example.deliveryApp.security.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    Set<Role> findAllByname(String customer);
}
