package com.example.demo.data.dto.requests;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private int otpFromVerify;
    private String newPassword;
}
