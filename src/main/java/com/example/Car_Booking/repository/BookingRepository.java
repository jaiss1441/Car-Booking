package com.example.Car_Booking.repository;

import com.example.Car_Booking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository <Booking, Integer> {
}
