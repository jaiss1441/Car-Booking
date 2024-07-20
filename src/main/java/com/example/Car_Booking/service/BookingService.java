package com.example.Car_Booking.service;


import com.example.Car_Booking.exception.UserNotFound;
import com.example.Car_Booking.models.Booking;
import com.example.Car_Booking.models.Customer;
import com.example.Car_Booking.repository.BookingRepository;
import com.example.Car_Booking.responsebody.BookingReponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<BookingReponseBody> getBookingByStatus(String state){
        List<Booking> bookingList = bookingRepository.getBookingByStatus(state);
        List<BookingReponseBody> bookingReponseBodyList = new ArrayList<>();
        for(Booking booking : bookingList){
            BookingReponseBody bookingReponseBody = new BookingReponseBody();
            bookingReponseBody.setBookingId(booking.getId());
            bookingReponseBody.setCustomerId(booking.getCustomer().getId());
            bookingReponseBody.setStartingLocation(booking.getStartingLocation());
            bookingReponseBody.setCustomerName(booking.getCustomer().getFirstName());
            bookingReponseBody.setDestinationLocation(booking.getDestinationLocation());
            bookingReponseBody.setBillingAmount(booking.getBillAmount());
            bookingReponseBody.setStatus(booking.getStatus());
            bookingReponseBodyList.add(bookingReponseBody);
        }
        return bookingReponseBodyList;
    }
}
