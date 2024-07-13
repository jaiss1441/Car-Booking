package com.example.Car_Booking.service;

import com.example.Car_Booking.exception.UserNotFound;
import com.example.Car_Booking.models.Customer;
import com.example.Car_Booking.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;
    public void RegisterAccount(Customer customer){customerRepo.save(customer);}

    public String authenticateCustomer(String email, String password){
    Customer customer=customerRepo.findByEmail(email);
        if(customer == null){
        throw new UserNotFound(String.format("User with %s does not exist",email));
        }
    String originalPassword=customer.getPassword();
    if(originalPassword.equals(password)){
        return "Authentication Successful";
    }
return "Authentication Fail";
    }


}
