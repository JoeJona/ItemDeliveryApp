package com.example.deliveryApp.security.services.impl;

import com.example.deliveryApp.security.entity.Customer;
import com.example.deliveryApp.security.entity.Role;
import com.example.deliveryApp.security.model.NewUserDetail;
import com.example.deliveryApp.security.repo.CustomerRepo;
import com.example.deliveryApp.security.repo.RoleRepo;
import com.example.deliveryApp.security.services.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public NewUserDetail saveUser(NewUserDetail newUserDetail) {
        Customer customer = new Customer();
        customer.setUserName(newUserDetail.getEmail());
        customer.setPassWord(newUserDetail.getUserPassword());
        customer.setCreatedOn(new Date());
        customer.setEnabled(true);
        customer.setLocked(false);
        customer.setIsSubscribe(true);
        customer.setRoles(roleRepo.findAllByname(newUserDetail.getRole()));

        customer.setPhoneNumber(newUserDetail.getPhoneNumber());
        customer.setFirstName(newUserDetail.getFirstName());
        customer.setLastName(newUserDetail.getLastName());
        customer.setCustomerPublicId(UUID.randomUUID().toString());


        customer.setPassWord(BCrypt.hashpw(customer.getPassWord(), BCrypt.gensalt()));
        Customer savedUser = customerRepo.save(customer);
        newUserDetail.setUserPublicId(savedUser.getCustomerPublicId());
        return newUserDetail;


    }

    @Override
    public Customer saveUser(Customer customer) {
        customer.setPassWord(BCrypt.hashpw(customer.getPassWord(), BCrypt.gensalt()));
        Customer savedUser = customerRepo.save(customer);
        return savedUser;


    }

    @Override
    public NewUserDetail saveUser(NewUserDetail newUserDetail, Set<Role> roles) {
        Customer customer = new Customer();
        customer.setUserName(newUserDetail.getEmail());
        customer.setPassWord(newUserDetail.getUserPassword());
        customer.setFirstName(newUserDetail.getFirstName());
        customer.setLastName(newUserDetail.getLastName());
        customer.setCreatedOn(new Date());
        customer.setEnabled(true);
        customer.setLocked(false);

        customer.setRoles(roles);

        customer.setPhoneNumber(newUserDetail.getPhoneNumber());

        String publicId = UUID.randomUUID().toString();
        customer.setCustomerPublicId(publicId);
        System.out.println(publicId);

        for(Role role:roles) {
            roleRepo.save(role);
        }

        customer.setPassWord(BCrypt.hashpw(customer.getPassWord(), BCrypt.gensalt()));
        Customer savedUser = customerRepo.save(customer);
        newUserDetail.setUserPublicId(savedUser.getCustomerPublicId());
        return newUserDetail;
    }

    public boolean isUserNameExists(String userName){
        Customer userInfo=customerRepo.findByUserName(userName);
        if(userInfo!=null)
            return true;
        return false;
    }

    @Override
    public Customer getUser(String userPublicId) {
        Optional<Customer> userByUserPublicId = customerRepo.findByCustomerPublicId(userPublicId);
        if(userByUserPublicId.isPresent()){
            return userByUserPublicId.get();
        }
        return null;
    }


}
