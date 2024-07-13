package com.example.Car_Booking.controller;

import com.example.Car_Booking.exception.DriverNotFound;
import com.example.Car_Booking.models.Driver;
import com.example.Car_Booking.requestBody.DriverCredentialRequestBody;
import com.example.Car_Booking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;
    @PostMapping("/api/driver/register")
    public String AccountCreaate(@RequestBody Driver driver){
        driverService.DriverRegistration(driver);
        return "Driver Register Successfully ";
    }

    @GetMapping("/api/driver/authenticate")
    public String DriverLogin(@RequestBody DriverCredentialRequestBody  driverCredentialRequestBody){
        String emailID = driverCredentialRequestBody.getEmailID();
        String password =driverCredentialRequestBody.getPassword();
        try{
            String isAuthenticateDriver= driverService.AuthenticateDriver(emailID,password);
            return isAuthenticateDriver;
        }catch (DriverNotFound driverNotFound){
            return driverNotFound.getMessage();
        }

    }

}
