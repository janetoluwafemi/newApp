package com.example.demo.data.repositories;

import com.example.demo.data.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    Optional<Payment> findPaymentByEmail(String email);
    Optional<Payment> findPaymentById(Long id);
}
