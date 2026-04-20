package com.example.demo.data.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AddProductWrapper {
    private String email;
    private AddProductRequest product;
}
