package com.example.deliveryApp.security.model;

import com.example.deliveryApp.security.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenPayLoad {
    private String token;
    private String pid;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;


    public TokenPayLoad(String token, Customer customer){
        this.token=token;

        if(customer!=null){
            this.pid=customer.getCustomerPublicId();
            this.firstName=customer.getFirstName();
            this.lastName=customer.getLastName();
            this.username=customer.getUserName();
            this.phone = customer.getPhoneNumber();
        }

    }
}
