package com.example.Car_Booking.repository;

import com.example.Car_Booking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository <Booking, Integer> {
    @Query(value = "SELECT * FROM booking where status =:state", nativeQuery = true)
    public List<Booking> getBookingByStatus(String state);
}
