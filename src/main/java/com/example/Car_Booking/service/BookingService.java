package com.example.Car_Booking.service;


import com.example.Car_Booking.exception.UserNotFound;
import com.example.Car_Booking.models.Booking;
import com.example.Car_Booking.models.Customer;
import com.example.Car_Booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    CustomerService customerService;
    @Autowired
    BookingRepository bookingRepository;
    public void handleCustomerRequest(String startingLocation, String destinationLocation,int customerId){
        //we need to validate is it  valid customer or not
        //if customer id  exists in our system so we will say customer is valid
        //else we will say customer is not valid
       Customer customer = customerService.getCustomerById(customerId);
        if (customer == null){
            throw  new UserNotFound(String.format("User with id %d  does not exist",customerId));
        }
        //Booking created not accepted
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setStatus("Draft");
        booking.setBillAmount(0);
        booking.setStartingLocation(startingLocation);
        booking.setDestinationLocation(destinationLocation);
        bookingRepository.save(booking);
    }
}