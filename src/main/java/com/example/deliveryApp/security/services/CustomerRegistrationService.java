package com.example.deliveryApp.security.services;

import com.example.deliveryApp.security.entity.Customer;
import com.example.deliveryApp.security.entity.Role;
import com.example.deliveryApp.security.model.NewUserDetail;

import java.util.Set;

public interface CustomerRegistrationService {

    NewUserDetail saveUser(NewUserDetail user);
    NewUserDetail saveUser(NewUserDetail user, Set<Role> roles);
    Customer saveUser(Customer customer);
    boolean isUserNameExists(String email);

    Customer getUser(String userPublicId);

}
