package com.example.demo.data.services;

import com.example.demo.data.dto.requests.*;
import com.example.demo.data.dto.responses.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    RegisterUserResponse registerUserResponse(RegisterUserRequest registerUserRequest);
    VerifyEmailForSignUpResponse verifyEmailResponse(VerifyEmailForSignUpRequest verifyEmailRequest);
    LoginUserResponse loginUserResponse(LoginUserRequest loginUserRequest);
    LogOutUserResponse logOutUserResponse(LogOutUserRequest logOutUserRequest);
    ChangePasswordResponse changePasswordResponse(ChangePasswordRequest changePasswordRequest);
    SendOTPResponse sendOTPResponse(String email);
    ResetPasswordResponse resetPasswordResponse(ResetPasswordRequest resetPasswordRequest);
    FindUserEmailResponse findUserEmailResponse(String email);
    AddProductResponse addProductResponse(String email, AddProductRequest addProductRequest);
    RemoveProductResponse removeProductResponse(String email, RemoveProductRequest removeProductRequest);
    GetProductResponse getProductResponse(Long productId);
    GetAllProductsResponse getAllProductsResponse();
    VerifyEmailResponse verifyEmailResponse(VerifyEmailRequest verifyEmailRequest);
    String message(Long userId);
    FindUserPaymentResponse findUserPaymentResponse(String email, FindUserPaymentRequest findUserPaymentRequest);
}
