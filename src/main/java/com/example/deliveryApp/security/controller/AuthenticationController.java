package com.example.deliveryApp.security.controller;

import com.example.deliveryApp.security.entity.Customer;
import com.example.deliveryApp.security.exception.UNAuthorizedException;
import com.example.deliveryApp.security.model.AuthenticationRequest;
import com.example.deliveryApp.security.model.CustomUserDetails;
import com.example.deliveryApp.security.model.NewUserDetail;
import com.example.deliveryApp.security.model.TokenPayLoad;
import com.example.deliveryApp.security.repo.CustomerRepo;
import com.example.deliveryApp.security.services.CustomerRegistrationService;
import com.example.deliveryApp.security.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/public")
public class AuthenticationController {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CustomerRegistrationService customerRegistrationService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API receive user's User Name and  Password and return the user's profile.")
    public TokenPayLoad authenticate(@Parameter(description = "User's Public Id") @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserName(),
                            authenticationRequest.getPassword()));
        } catch (Exception e) {
            throw new UNAuthorizedException("Username or/and password is not correct.");
        }
        Customer userByUserName = customerRepo.findByUserName(authenticationRequest.getUserName());
        CustomUserDetails customUserDetails = new CustomUserDetails(userByUserName);
        String generatedToken = jwtTokenUtil.generateToken(customUserDetails);
        return new TokenPayLoad(generatedToken,userByUserName);

    }

    @PostMapping(value = "/createCustomer")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API receive User's Information and then Create New USer and return the user's profile.")
    ResponseEntity<NewUserDetail> createUser(@Parameter(description = "User's Information") @RequestBody NewUserDetail userDetail) throws Exception{
        System.out.println("it is coming to save the user");
        NewUserDetail savedUserDetail=null;
        if(!customerRegistrationService.isUserNameExists(userDetail.getEmail())) {
            savedUserDetail = customerRegistrationService.saveUser(userDetail);
            return ResponseEntity.created(new URI("/users/createUser"))
                    .body(savedUserDetail);
        }else{
            return new ResponseEntity(userDetail, HttpStatus.CONFLICT);
        }


    }
}
