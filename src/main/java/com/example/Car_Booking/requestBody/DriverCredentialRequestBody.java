package com.example.Car_Booking.requestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverCredentialRequestBody {
    String emailID;
    String password;
}
