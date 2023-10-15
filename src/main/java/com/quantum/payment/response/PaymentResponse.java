package com.quantum.payment.response;
//author : Jere

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private int id;
    private String bank;
    private String city;
    private String state;
}
