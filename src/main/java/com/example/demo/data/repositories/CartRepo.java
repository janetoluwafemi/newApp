package com.example.demo.data.repositories;

import com.example.demo.data.models.Cart;
import com.example.demo.data.models.CartItem;
import com.example.demo.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartById(Long id);
}
