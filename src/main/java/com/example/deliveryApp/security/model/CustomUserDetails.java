package com.example.deliveryApp.security.model;

import com.example.deliveryApp.security.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
public class CustomUserDetails implements UserDetails {

    Customer customer;


    public CustomUserDetails(Customer user) {
        if(user != null) {
            this.customer = user;
        } else {
            this.customer = new Customer();
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autorities=new HashSet<>();
        this.customer.getRoles().forEach(ur->autorities.add(new Authority(ur.getName())));
        return autorities;
    }

    @Override
    public String getPassword() {
        return this.customer.getPassWord();
    }

    @Override
    public String getUsername() {
        return this.customer.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
