package com.example.demo.data.dto.requests;

import com.example.demo.data.models.Cart;
import com.example.demo.data.models.CartItem;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class AddToCartRequest {
    private Long userId;
    private Long productId;
    private int quantity;
}
