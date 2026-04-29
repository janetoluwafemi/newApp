package com.example.demo.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private int amount;
    private String reference;
}
