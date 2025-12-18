package com.example.demo;

import com.example.demo.data.dto.requests.LoginUserRequest;
import com.example.demo.data.dto.requests.RegisterUserRequest;
import com.example.demo.data.dto.requests.ResetPasswordRequest;
import com.example.demo.data.dto.requests.VerifyEmailRequest;
import com.example.demo.data.dto.responses.LoginUserResponse;
import com.example.demo.data.dto.responses.RegisterUserResponse;
import com.example.demo.data.dto.responses.ResetPasswordResponse;
import com.example.demo.data.dto.responses.VerifyEmailResponse;
import com.example.demo.data.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest

public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Test
    public void testRegisterUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("OluwafemiJanet85@gmail.com");
        request.setPassword("janet@2007");
        request.setFirstName("Janet");
        request.setLastName("Oluwafemi");
        RegisterUserResponse registerUserResponse = userService.registerUserResponse(request);
        assertThat(registerUserResponse.getMessage().equals("User Registered successfully"));
    }
    @Test
    public void testUserVerification() {
        VerifyEmailRequest request = new VerifyEmailRequest();
        request.setEmail("OluwafemiJanet@gmail.com");
        request.setOtp(1234);
        VerifyEmailResponse verifyEmailResponse = userService.verifyEmailResponse(request);
        assertThat(verifyEmailResponse.getMessage().equals("OTP verified successfully"));
    }
    @Test
    public void testLoginUser() {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("Oluwafemi@gmail.com");
        request.setPassword("Janet@2007");
        LoginUserResponse loginUserResponse = userService.loginUserResponse(request, authenticationManager);
        assertThat(loginUserResponse.getMessage()).isEqualTo("User logged in successfully");
        assertThat(loginUserResponse.getToken()).isNotNull();
        assertThat(loginUserResponse.getToken()).isNotEmpty();
    }
    @Test
    public void testResetPassword() {
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("Oluwafemi@gmail.com");
        ResetPasswordResponse resetPasswordResponse = userService.resetPasswordResponse(request);
        assertThat(resetPasswordResponse.getToken()).isNotNull();
        request.setPassword("OluwafemiJanet@2007");
        request.setOtp(null);
        ResetPasswordResponse resetPasswordResponse2 = userService.resetPasswordResponse(request);
        assertThat(resetPasswordResponse2.getMessage()).isEqualTo("Password Reset Successfully");
    }
}
