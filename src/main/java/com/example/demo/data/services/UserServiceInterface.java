package com.example.demo.data.services;

import com.example.demo.data.dto.requests.*;
import com.example.demo.data.dto.responses.*;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserServiceInterface extends UserDetailsService {
    RegisterUserResponse registerUserResponse(RegisterUserRequest registerUserRequest);
    VerifyEmailResponse verifyEmailResponse(VerifyEmailRequest verifyEmailRequest);
    LoginUserResponse loginUserResponse(LoginUserRequest loginUserRequest);
    LogOutUserResponse logOutUserResponse(LogOutUserRequest logOutUserRequest);
    ChangePasswordResponse changePasswordResponse(ChangePasswordRequest changePasswordRequest);
    SendOTPResponse sendOTPResponse(String email);
    ResetPasswordResponse resetPasswordResponse(String email, ResetPasswordRequest resetPasswordRequest);
    FindUserEmailResponse findUserEmailResponse(String email);
    AddProductResponse addProductResponse(String email, AddProductRequest addProductRequest);
    RemoveProductResponse removeProductResponse(RemoveProductRequest removeProductRequest);
}
