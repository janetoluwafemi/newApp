package com.example.demo.data.dto.responses;

import com.example.demo.data.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class MakeReviewAboutProductResponse {
    private User user;
    private String review;
    private String message;
}
