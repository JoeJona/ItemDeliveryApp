package com.example.deliveryApp.security.services;

import com.example.deliveryApp.security.entity.Customer;
import com.example.deliveryApp.security.model.CustomUserDetails;
import com.example.deliveryApp.security.repo.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String userName){
        Customer userUserName = customerRepo.findByUserName(userName);
        if(userUserName == null){
//            throw new UsernameNotFoundException("Could not find user");
            log.error("Error occured when User name: {} is trying to access",userName);
        }
        return new CustomUserDetails(userUserName);
    }
}
