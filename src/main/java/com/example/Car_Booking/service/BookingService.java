package com.example.Car_Booking.service;


import com.example.Car_Booking.exception.InvalidOperationException;
import com.example.Car_Booking.exception.ResourceDoesNotExist;
import com.example.Car_Booking.exception.UserNotFound;
import com.example.Car_Booking.models.AppUser;
import com.example.Car_Booking.models.Booking;
import com.example.Car_Booking.models.Customer;
import com.example.Car_Booking.models.Driver;
import com.example.Car_Booking.repository.BookingRepository;
import com.example.Car_Booking.responsebody.BookingReponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    CustomerService customerService;
    @Autowired
    DriverService driverService;
    @Autowired
    BookingRepository bookingRepository;

    public void handleCustomerRequest(String startingLocation, String destinationLocation, int customerId) {
        //we need to validate is it  valid customer or not
        //if customer id  exists in our system so we will say customer is valid
        //else we will say customer is not valid
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new UserNotFound(String.format("User with id %d  does not exist", customerId));
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

    public List<BookingReponseBody> getBookingByStatus(String state) {
        List<Booking> bookingList = bookingRepository.getBookingByStatus(state);
        List<BookingReponseBody> bookingReponseBodyList = new ArrayList<>();
        for (Booking booking : bookingList) {
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

    public String updateBooking(String operation,
                                String email,
                                Integer bookingId) {
        Customer customer = customerService.getCustomerByEmail(email);
        Driver driver = driverService.getDriverByemail(email);
        String userType = "";
        AppUser appUser = null;
        Integer userId= -1;
        if (customer != null) {
            userId = customer.getId();
            userType = "CUSTOMER";
            appUser = customer;
        } else if (driver != null) {
            userId = driver.getId();
            userType = "DRIVER";
            appUser = driver;
        } else {
            throw new UserNotFound(String.format("User with id %d does not exist", userId));
        }
        Booking booking = bookingRepository.findById(bookingId).orElse(null);

        if (booking == null) {
            throw new ResourceDoesNotExist(String.format("Booking with %d does not exist in System", bookingId));

        }


        if (operation.equals("ACCEPT")) {
            if (userType.equals("CUSTOMER")) {
                throw new InvalidOperationException(String.format("Customer cn not accept rides"));
            }
            //driver to accept the ride

            booking.setDriver(driver);
            booking.setStatus("ACCEPTED");
            booking.setBillAmount(100);
            bookingRepository.save(booking);
            return String.format("Driver id %d accepted booking with id %d",userId,bookingId);
        } else if (operation.equals("CANCEL")) {
            if (userType.equals("CUSTOMER")) {
                if (booking.getCustomer().getId() == userId) {
                    booking.setStatus("CANCELED");
                    bookingRepository.save(booking);
                    return String.format("Customer with id %d cancelled ride with booking id %d", userId, bookingId);
                }else {
                    throw new InvalidOperationException(String.format("Customer %d not allowed to cancel booking with id %d",userId,bookingId));
                }
            } else if (userType.equals("DRIVER")) {
                if(booking.getDriver().getId()==userId){
                    booking.setStatus("CANCELLED");
                    bookingRepository.save(booking);
                    return String.format("Driver with id %d cancelled booking with id %d",userId,bookingId);
                }else {
                    throw new InvalidOperationException(String.format("Driver %d not allowed to cancel booking with id %d",userId,bookingId));
                }

            }
        }
        return "";

    }

}
