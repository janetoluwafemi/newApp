package com.example.demo.data.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RemoveProductWrapper {
    private String email;
    private RemoveProductRequest product;
}
