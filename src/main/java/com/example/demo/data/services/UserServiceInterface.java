package com.example.demo.data.services;

import com.example.demo.data.dto.requests.*;
import com.example.demo.data.dto.responses.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserServiceInterface extends UserDetailsService {
    RegisterUserResponse registerUserResponse(RegisterUserRequest registerUserRequest);
    VerifyEmailResponse verifyEmailResponse(VerifyEmailRequest verifyEmailRequest);
    LoginUserResponse loginUserResponse(LoginUserRequest loginUserRequest, AuthenticationManager authenticationManager);
    LogOutUserResponse logOutUserResponse(LogOutUserRequest logOutUserRequest);
    ChangePasswordResponse changePasswordResponse(ChangePasswordRequest changePasswordRequest);
    ResetPasswordResponse resetPasswordResponse(ResetPasswordRequest resetPasswordRequest);
    FindUserEmailResponse findUserEmailResponse(String email);
}
