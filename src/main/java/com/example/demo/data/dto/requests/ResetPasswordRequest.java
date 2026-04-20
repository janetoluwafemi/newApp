package com.example.demo.data.dto.requests;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String newPassword;
    private int otp;
}
