package com.example.demo.data.dto.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class AddProductResponse {
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private String category;
    private String description;
    private String imageUrl;
    private String message;
}
