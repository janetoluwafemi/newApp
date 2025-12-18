package com.example.demo.data.dto.requests;

import lombok.Data;

@Data
public class VerifyEmailRequest {
    private String email;
    private int otp;
}
