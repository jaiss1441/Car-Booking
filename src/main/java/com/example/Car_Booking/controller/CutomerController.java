package com.example.Car_Booking.controller;

import com.example.Car_Booking.exception.UserNotFound;
import com.example.Car_Booking.models.Customer;
import com.example.Car_Booking.requestBody.UserCredentialRequest;
import com.example.Car_Booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CutomerController {

    @Autowired
    CustomerService customerService;


    @PostMapping("/register")
    public String customerRegistration(@RequestBody Customer customer){
        System.out.println("done");
        customerService.RegisterAccount(customer);
        return "Successfully register";
    }

    @GetMapping("/authenticate")
    public String loginCustomer(@RequestBody UserCredentialRequest userCredentialRequest) {
        String email = userCredentialRequest.getEmail();
        String password = userCredentialRequest.getPassword();
        try {
            String isAuthenticates = customerService.authenticateCustomer(email, password);
            return isAuthenticates;
        } catch (UserNotFound userNotFound) {
            return userNotFound.getMessage();
        }


    }
}
