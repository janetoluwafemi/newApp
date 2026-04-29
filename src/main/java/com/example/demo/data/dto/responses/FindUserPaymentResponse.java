package com.example.demo.data.dto.responses;

import com.example.demo.data.models.Payment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class FindUserPaymentResponse {
    private Payment payment;
    private String message;
}
