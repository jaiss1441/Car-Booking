package com.example.Car_Booking.requestBody;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBookingRequestBody {
    private String StartingLocation;
    private String DestinationLocation;
}
