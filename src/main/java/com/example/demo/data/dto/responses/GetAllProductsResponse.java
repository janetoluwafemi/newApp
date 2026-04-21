package com.example.demo.data.dto.responses;

import com.example.demo.data.models.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class GetAllProductsResponse {
    private List<Product> products;
    private String message;
}
