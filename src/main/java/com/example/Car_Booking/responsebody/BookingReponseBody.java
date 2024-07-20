package com.example.Car_Booking.responsebody;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingReponseBody {

    private int bookingId;
    private int customerId;
    private String customerName;
    private String startingLocation;
    private  String destinationLocation;
    private int billingAmount;
    private String status;
}
