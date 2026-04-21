package com.example.demo.data.dto.responses;

import com.example.demo.data.models.Reviews;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class GetAllReviewsResponse {
    private List<Reviews> reviews;
    private String message;
}
