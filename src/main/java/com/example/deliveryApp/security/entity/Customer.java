package com.example.deliveryApp.security.entity;

import com.example.deliveryApp.businesslogic.model.CustomerProfileModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Integer id;
    @Column(name = "USER_NAME")
    String userName;
    @Column(name = "PASSWORD")
    String passWord;
    @Column(name = "IS_LOCKED")
    boolean isLocked;
    @Column(name = "IS_ENABLED")
    boolean isEnabled;
    @Column(name = "IS_SUBSCRIBE")
    Boolean isSubscribe;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="ROLE_CUSTOMER",joinColumns=@JoinColumn(name="CUSTOMER_ID"),inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
    @JsonIgnoreProperties("users")
    private Set<Role> roles=new HashSet<>();

    @Column(name = "CREATED_ON")
    Date createdOn;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "USER_PUBLIC_ID")
    private String customerPublicId;

    public Customer fromUserProfile(CustomerProfileModel customerProfileModel){
        return this.builder()
                .userName(customerProfileModel.getEmail())
                .passWord(customerProfileModel.getUserPassword())
                .firstName(customerProfileModel.getFirstName())
                .lastName(customerProfileModel.getLastName())
                .customerPublicId(customerProfileModel.getCustomerPublicId())
                .build();
    }
}
