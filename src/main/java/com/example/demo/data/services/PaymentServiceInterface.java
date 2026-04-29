package com.example.demo.data.services;

import com.example.demo.data.dto.requests.InitializePaymentRequest;
import com.example.demo.data.dto.requests.VerifyPaymentRequest;
import com.example.demo.data.dto.responses.InitializePaymentResponse;
import com.example.demo.data.dto.responses.VerifyPaymentResponse;

public interface PaymentServiceInterface {
    InitializePaymentResponse initializePaymentResponse(InitializePaymentRequest initializePaymentRequest);
    VerifyPaymentResponse verifyPaymentResponse(VerifyPaymentRequest verifyPaymentRequest);
}
