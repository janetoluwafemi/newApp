package com.example.demo.data.dto.responses;

import com.example.demo.data.models.Product;
import lombok.Data;

@Data
public class MakeAnOrderResponse {
    private Long orderId;
    private String message;
    private Product product;
}
