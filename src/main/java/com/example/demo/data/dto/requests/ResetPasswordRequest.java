package com.example.demo.data.dto.requests;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String password;
    private String otp;
    private String email;
}
