package com.example.Car_Booking.controller;

import com.example.Car_Booking.exception.InvalidOperationException;
import com.example.Car_Booking.exception.ResourceDoesNotExist;
import com.example.Car_Booking.exception.UserNotFound;
import com.example.Car_Booking.models.Booking;
import com.example.Car_Booking.requestBody.CustomerBookingRequestBody;
import com.example.Car_Booking.responsebody.BookingReponseBody;
import com.example.Car_Booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

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

    @GetMapping("/all")
    public List<BookingReponseBody> getBookingByStatus(@RequestParam String state){
        return bookingService.getBookingByStatus(state);

    }

    @PutMapping("/update")
    public ResponseEntity updateBookingStte(@RequestParam String opr
            , @RequestParam String  email
            , @RequestParam Integer bookingId){
        //we need to identify is this a customer email or is this a driver Email
        try {
            String response = bookingService.updateBooking(opr,email,bookingId);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (UserNotFound userNotFound){
            return new ResponseEntity(userNotFound.getMessage(),HttpStatus.NOT_FOUND);
        }catch (InvalidOperationException invalidOperationException){
            return new ResponseEntity(invalidOperationException.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
        }catch (ResourceDoesNotExist resourceDoesNotExist){
            return new ResponseEntity(resourceDoesNotExist.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
