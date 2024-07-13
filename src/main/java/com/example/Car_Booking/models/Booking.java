package com.example.Car_Booking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    Customer customer;
    private int billAmount;
    private String status;
    private String feedback;
    @ManyToOne
    Driver driver;
    private String StartingLocation;
    private String DestinationLocation;

    public Booking() {
    }

    public Booking(int id, Customer customer, int billAmount, String status, String feedback, Driver driver) {
        this.id = id;
        this.customer = customer;
        this.billAmount = billAmount;
        this.status = status;
        this.feedback = feedback;
        this.driver = driver;
    }

}
