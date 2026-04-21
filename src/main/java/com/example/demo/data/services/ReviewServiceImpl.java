package com.example.demo.data.services;

import com.example.demo.data.dto.requests.DeleteReviewAboutProductRequest;
import com.example.demo.data.dto.requests.GetAllReviewsRequest;
import com.example.demo.data.dto.requests.MakeReviewAboutProductRequest;
import com.example.demo.data.dto.responses.DeleteReviewAboutProductResponse;
import com.example.demo.data.dto.responses.GetAllReviewsResponse;
import com.example.demo.data.dto.responses.MakeReviewAboutProductResponse;
import com.example.demo.data.exceptions.ProductDoesNotExistException;
import com.example.demo.data.exceptions.ReviewNotFoundException;
import com.example.demo.data.exceptions.UserNotFoundException;
import com.example.demo.data.models.Product;
import com.example.demo.data.models.Reviews;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.ProductRepo;
import com.example.demo.data.repositories.ReviewsRepo;
import com.example.demo.data.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class ReviewServiceImpl implements ReviewServiceInterface{
    @Autowired
    private ReviewsRepo reviewsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public MakeReviewAboutProductResponse makeReviewAboutProductResponse(MakeReviewAboutProductRequest makeReviewAboutProductRequest) {
        Optional<User> user = userRepo.findUserById(makeReviewAboutProductRequest.getUserId());
        Optional<Product> product = productRepo.findProductById(makeReviewAboutProductRequest.getProductId());
        if (user.isPresent()) {
            if (product.isPresent()) {
                Reviews reviews = new Reviews();
                reviews.setProduct(product.get());
                reviews.setUser(user.get());
                reviews.setReview(makeReviewAboutProductRequest.getReview());
                reviewsRepo.save(reviews);
                MakeReviewAboutProductResponse makeReviewAboutProductResponse = new MakeReviewAboutProductResponse();
                makeReviewAboutProductResponse.setUser(reviews.getUser());
                makeReviewAboutProductResponse.setReview(makeReviewAboutProductRequest.getReview());
                makeReviewAboutProductResponse.setMessage("Review successfully made");
                return makeReviewAboutProductResponse;
            }
            throw new ProductDoesNotExistException("Product does not exist");
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public DeleteReviewAboutProductResponse deleteReviewAboutProductResponse(DeleteReviewAboutProductRequest deleteReviewAboutProductRequest) {
        Optional<User> user = userRepo.findUserById(deleteReviewAboutProductRequest.getUserId());
        if (user.isPresent()) {
            Reviews reviews = reviewsRepo.findReviewsById(deleteReviewAboutProductRequest.getReviewId())
                    .orElseThrow(() -> new ReviewNotFoundException("Review not found"));
            reviewsRepo.delete(reviews);
            DeleteReviewAboutProductResponse deleteReviewAboutProductResponse = new DeleteReviewAboutProductResponse();
            deleteReviewAboutProductResponse.setMessage("Review deleted successfully");
            return deleteReviewAboutProductResponse;
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public GetAllReviewsResponse getAllReviewsResponse(GetAllReviewsRequest getAllReviewsRequest) {
        Optional<User> user = userRepo.findUserById(getAllReviewsRequest.getUserId());
        if (user.isPresent()) {
            List<Reviews> reviews = reviewsRepo.findAll();
            GetAllReviewsResponse getAllReviewsResponse = new GetAllReviewsResponse();
            getAllReviewsResponse.setReviews(reviews);
            getAllReviewsResponse.setMessage("All reviews successfully retrieved");
            return getAllReviewsResponse;
        }
        throw new UserNotFoundException("User not found");
    }
}
