package com.example.demo.data.controllers;

import com.example.demo.data.dto.requests.*;
import com.example.demo.data.dto.responses.*;
import com.example.demo.data.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            LoginUserResponse loginUserResponse = userService.loginUserResponse(loginUserRequest);
            return new ResponseEntity<>(new ApiResponse(loginUserResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody String email, ResetPasswordRequest resetPasswordRequest) {
        try {
            ResetPasswordResponse resetPasswordResponse = userService.resetPasswordResponse(email, resetPasswordRequest);
            return new ResponseEntity<>(new ApiResponse(resetPasswordResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            ChangePasswordResponse changePasswordResponse = userService.changePasswordResponse(changePasswordRequest);
            return new ResponseEntity<>(new ApiResponse(changePasswordResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody AddProductWrapper addProductRequest) {
        try {
            String email = addProductRequest.getEmail();
            AddProductRequest product = addProductRequest.getProduct();
            AddProductResponse addProductResponse = userService.addProductResponse(email, product);
            return new ResponseEntity<>(new ApiResponse(addProductResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestBody RemoveProductWrapper removeProductRequest) {
        try {
            String email = removeProductRequest.getEmail();
            RemoveProductRequest product = removeProductRequest.getProduct();
            RemoveProductResponse removeProductResponse = userService.removeProductResponse(email, product);
            return new ResponseEntity<>(new ApiResponse(removeProductResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts(@RequestParam String token) {
        try {
            GetAllProductsResponse getAllProductsResponse = userService.getAllProductsResponse(token);
            return new ResponseEntity<>(new ApiResponse(getAllProductsResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
}
