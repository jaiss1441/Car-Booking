package com.example.Car_Booking.repository;

import com.example.Car_Booking.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {


    public  Driver findByEmailID(String emailID);

}
