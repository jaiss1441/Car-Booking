package com.example.Car_Booking.controller;

import com.example.Car_Booking.exception.UserNotFound;
import com.example.Car_Booking.requestBody.CustomerBookingRequestBody;
import com.example.Car_Booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;
    @PostMapping("/request")
    public String  createCustomerBooking(@RequestBody CustomerBookingRequestBody customerBookingRequestBody,
                                      @RequestParam int customerId){
        String startingLocation = customerBookingRequestBody.getStartingLocation();
        String destinationLocation = customerBookingRequestBody.getDestinationLocation();

        try {
            bookingService.handleCustomerRequest(startingLocation,destinationLocation,customerId);
            return "Waiting for Driver to accept";
        }catch (UserNotFound userNotFound){
            return userNotFound.getMessage();
        }
    }
}
