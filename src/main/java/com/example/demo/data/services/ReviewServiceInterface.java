package com.example.demo.data.services;

import com.example.demo.data.dto.requests.DeleteReviewAboutProductRequest;
import com.example.demo.data.dto.requests.GetAllReviewsRequest;
import com.example.demo.data.dto.requests.MakeReviewAboutProductRequest;
import com.example.demo.data.dto.responses.DeleteReviewAboutProductResponse;
import com.example.demo.data.dto.responses.GetAllReviewsResponse;
import com.example.demo.data.dto.responses.MakeReviewAboutProductResponse;

public interface ReviewServiceInterface {
    MakeReviewAboutProductResponse makeReviewAboutProductResponse(MakeReviewAboutProductRequest makeReviewAboutProductRequest);
    DeleteReviewAboutProductResponse deleteReviewAboutProductResponse(DeleteReviewAboutProductRequest deleteReviewAboutProductRequest);
    GetAllReviewsResponse getAllReviewsResponse(GetAllReviewsRequest getAllReviewsRequest);
}
