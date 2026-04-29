package com.example.demo.data.dto.responses;

import com.example.demo.data.models.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GetProductResponse {
    private Product product;
    private String message;
}
