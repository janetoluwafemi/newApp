package com.example.demo.data.dto.requests;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class MakeAnOrderRequest {
    @Column(unique = true)
    private String email;

    private int quantity;
}
