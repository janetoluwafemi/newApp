package com.example.demo.data.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class AddProductRequest {
    private String productName;
    private double price;
    private int quantity;
    private String category;
    private String description;
    private String imageUrl;
}
