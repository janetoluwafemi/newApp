package com.example.demo.data.repositories;

import com.example.demo.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    Optional<Product> findProductByProductName(String productName);
}
