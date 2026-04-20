package com.example.demo.data.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class RemoveFromCartRequest {
    private Long userId;
    private Long productId;
    private Long cartItemId;
}
