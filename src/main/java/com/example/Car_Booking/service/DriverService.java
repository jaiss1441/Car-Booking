package com.example.Car_Booking.service;

import com.example.Car_Booking.exception.DriverNotFound;
import com.example.Car_Booking.models.Driver;
import com.example.Car_Booking.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;

    public void DriverRegistration(Driver driver){driverRepository.save(driver);}

    public String AuthenticateDriver(String emailID, String password){
        Driver driver= driverRepository.findByEmailID(emailID);
        if(driver==null){
            throw new DriverNotFound(String.format("Driver with %s does not exist",emailID));
        }
        String originalPassword=driver.getPassword();
        if(originalPassword.equals(password)){
            return "Authentication Successful";
        }
        return "Authentication Fail";
    }
    public Driver getDriverByemail(String emailId){
        return driverRepository.findByEmailID(emailId);
    }
}
