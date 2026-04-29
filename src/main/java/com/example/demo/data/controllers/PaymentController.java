package com.example.demo.data.controllers;

import com.example.demo.data.dto.requests.InitializePaymentRequest;
import com.example.demo.data.dto.requests.VerifyPaymentRequest;
import com.example.demo.data.dto.responses.ApiResponse;
import com.example.demo.data.dto.responses.InitializePaymentResponse;
import com.example.demo.data.dto.responses.VerifyPaymentResponse;
import com.example.demo.data.services.PaymentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://email-app-chi.vercel.app"})
@RequestMapping("/api/v1/auth")
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initializePayment")
    public ResponseEntity<?> initializePayment(@RequestBody InitializePaymentRequest request) {
        try {
            InitializePaymentResponse response = paymentService.initializePaymentResponse(request);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/verifyPayment")
    public ResponseEntity<?> verifyPayment(@RequestBody VerifyPaymentRequest request) {
        try {
            VerifyPaymentResponse response = paymentService.verifyPaymentResponse(request);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
}
