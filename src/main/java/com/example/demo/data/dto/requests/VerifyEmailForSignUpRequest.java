package com.example.demo.data.dto.requests;

import lombok.Data;

@Data
public class VerifyEmailForSignUpRequest {
    private String email;
    private int otp;
}
