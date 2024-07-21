package com.example.Car_Booking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Driver implements AppUser{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String licenceID;
    private String vehicleType;
    private String firstName;
    private String lastName;
    @Column(length = 10, unique = true, nullable = false)
    private Long phoneNumber;
    @Column(unique = true)
    private String emailID;
    private String password;
    private Double rating;
    private int totalRideServed;
    @OneToMany(mappedBy = "driver")
    private List<Booking> bookings;


    public Driver() {
    }

    public Driver(int id, String licenceID, String vehicleType, String firstName, String lastName, Long phoneNumber, String emailID, String password, Double rating, int totalRideServed, List<Booking> bookings) {
        this.id = id;
        this.licenceID = licenceID;
        this.vehicleType = vehicleType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
        this.password = password;
        this.rating = rating;
        this.totalRideServed = totalRideServed;
        this.bookings = bookings;
    }

}
