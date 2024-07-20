package com.example.Car_Booking.controller;

import com.example.Car_Booking.exception.DriverNotFound;
import com.example.Car_Booking.models.Driver;
import com.example.Car_Booking.requestBody.DriverCredentialRequestBody;
import com.example.Car_Booking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    DriverService driverService;
    @PostMapping("/register")
    public String AccountCreaate(@RequestBody Driver driver){
        driverService.DriverRegistration(driver);
        return "Driver Register Successfully ";
    }

    @GetMapping("/authenticate")
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
