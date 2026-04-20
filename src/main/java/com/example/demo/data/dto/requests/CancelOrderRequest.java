package com.example.demo.data.dto.requests;

import lombok.Data;

@Data
public class CancelOrderRequest {
    private String email;
    private Long userId;
    private Long orderId;
}
