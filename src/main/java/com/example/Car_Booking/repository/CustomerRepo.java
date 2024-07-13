package com.example.Car_Booking.repository;


import com.example.Car_Booking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    public Customer findByEmail(String email);
}
