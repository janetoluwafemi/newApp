package com.example.demo.data.dto.responses;


import com.example.demo.data.models.CartItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class AddToCartResponse {
    private CartItem cartItem;
    private String message;
}
