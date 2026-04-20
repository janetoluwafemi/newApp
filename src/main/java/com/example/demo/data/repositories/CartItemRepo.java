package com.example.demo.data.repositories;

import com.example.demo.data.models.Cart;
import com.example.demo.data.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartItemById(Long id);
}
