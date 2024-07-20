package com.example.Car_Booking.models;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     int id;
    @ManyToOne
    Customer customer;
     int billAmount;
     String status;
     String feedback;
    @ManyToOne
    Driver driver;
    private String startingLocation;
    private String destinationLocation;


}
