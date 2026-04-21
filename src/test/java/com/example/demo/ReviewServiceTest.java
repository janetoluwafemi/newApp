package com.example.demo;

import com.example.demo.data.dto.requests.DeleteReviewAboutProductRequest;
import com.example.demo.data.dto.requests.GetAllReviewsRequest;
import com.example.demo.data.dto.requests.MakeReviewAboutProductRequest;
import com.example.demo.data.dto.responses.DeleteReviewAboutProductResponse;
import com.example.demo.data.dto.responses.GetAllReviewsResponse;
import com.example.demo.data.dto.responses.MakeReviewAboutProductResponse;
import com.example.demo.data.services.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class ReviewServiceTest {
    @Autowired
    private ReviewServiceImpl reviewService;

    @Test
    public void testUserMakeReview() {
        MakeReviewAboutProductRequest request = new MakeReviewAboutProductRequest();
        request.setUserId(1L);
        request.setProductId(2L);
        request.setReview("I’ve been using this hair cream for a few weeks and I’m really impressed. It makes my hair feel softer, more moisturized, and much easier to manage. It also reduces dryness and frizz without making my hair greasy. Overall, it’s a great product for daily hair care and I would definitely recommend it.");
        MakeReviewAboutProductResponse response = reviewService.makeReviewAboutProductResponse(request);
        assertThat(response.getMessage().equals("Review successfully made"));
    }
//    @Test
//    public void testUserDeleteReview() {
//        DeleteReviewAboutProductRequest request = new DeleteReviewAboutProductRequest();
//        request.setUserId(1L);
//        request.setReviewId(1L);
//        DeleteReviewAboutProductResponse response = reviewService.deleteReviewAboutProductResponse(request);
//        assertThat(response.getMessage().equals("Review deleted successfully"));
//    }
//    @Test
//    public void testGetAllReviews() {
//        GetAllReviewsRequest getAllReviewsRequest = new GetAllReviewsRequest();
//        getAllReviewsRequest.setUserId(1L);
//        GetAllReviewsResponse getAllReviewsResponse = reviewService.getAllReviewsResponse(getAllReviewsRequest);
//        assertThat(getAllReviewsResponse.getMessage().equals("All reviews successfully retrieved"));
//    }
}
