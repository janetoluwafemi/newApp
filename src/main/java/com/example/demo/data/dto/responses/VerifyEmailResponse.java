package com.example.demo.data.dto.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class VerifyEmailResponse {
    private String otp;
    private String message;
}
