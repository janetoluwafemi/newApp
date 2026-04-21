package com.example.demo.data.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class MakeReviewAboutProductRequest {
    private Long userId;
    private Long productId;
    private String review;
}
