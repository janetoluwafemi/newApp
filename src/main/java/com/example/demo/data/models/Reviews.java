package com.example.demo.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    @Lob
    private String review;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
