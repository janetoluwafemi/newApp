package com.example.demo.data.repositories;

import com.example.demo.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    Optional<Product> getProductById(Long id);
    Optional<Product> findProductByProductName(String productName);
}
