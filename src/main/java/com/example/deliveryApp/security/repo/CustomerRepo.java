package com.example.deliveryApp.security.repo;

import com.example.deliveryApp.security.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    Customer findByUserName(String userName);

    Optional<Customer> findByCustomerPublicId(String publicId);

    Customer getByCustomerPublicId(String publicId);

    List<Customer> findUserByIsSubscribeEquals(Boolean isSubscribe);

}
