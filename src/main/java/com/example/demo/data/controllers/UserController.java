package com.example.demo.data.controllers;

import com.example.demo.data.dto.requests.LoginUserRequest;
import com.example.demo.data.dto.requests.RegisterUserRequest;
import com.example.demo.data.dto.requests.VerifyEmailRequest;
import com.example.demo.data.dto.responses.ApiResponse;
import com.example.demo.data.dto.responses.LoginUserResponse;
import com.example.demo.data.dto.responses.RegisterUserResponse;
import com.example.demo.data.dto.responses.VerifyEmailResponse;
import com.example.demo.data.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://email-app-chi.vercel.app"})
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            RegisterUserResponse registerUserResponse = userService.registerUserResponse(registerUserRequest);
            return new ResponseEntity<>(new ApiResponse(registerUserResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest verifyEmailRequest) {
        try {
            VerifyEmailResponse verifyEmailResponse = userService.verifyEmailResponse(verifyEmailRequest);
            return new ResponseEntity<>(new ApiResponse(verifyEmailResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest loginUserRequest, AuthenticationManager authenticationManager) {
        try {
            LoginUserResponse loginUserResponse = userService.loginUserResponse(loginUserRequest, authenticationManager);
            return new ResponseEntity<>(new ApiResponse(loginUserResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
}
