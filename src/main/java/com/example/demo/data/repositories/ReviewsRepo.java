package com.example.demo.data.repositories;

import com.example.demo.data.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewsRepo extends JpaRepository<Reviews, Long> {
    Optional<Reviews> findReviewsById(Long id);
}
