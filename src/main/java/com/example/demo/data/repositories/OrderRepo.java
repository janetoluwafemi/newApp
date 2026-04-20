package com.example.demo.data.repositories;

import com.example.demo.data.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findOrderById(Long orderId);
}
