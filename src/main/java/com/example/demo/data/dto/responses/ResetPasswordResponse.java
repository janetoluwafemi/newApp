package com.example.demo.data.dto.responses;

import lombok.Data;

@Data
public class ResetPasswordResponse {
    private String message;
    private Long token;
}
