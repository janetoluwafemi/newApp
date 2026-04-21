package com.example.demo.data.controllers;

import com.example.demo.data.dto.requests.AddToCartRequest;
import com.example.demo.data.dto.requests.DeleteCartRequest;
import com.example.demo.data.dto.requests.RemoveFromCartRequest;
import com.example.demo.data.dto.responses.AddToCartResponse;
import com.example.demo.data.dto.responses.ApiResponse;
import com.example.demo.data.dto.responses.DeleteCartResponse;
import com.example.demo.data.dto.responses.RemoveFromCartResponse;
import com.example.demo.data.services.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://email-app-chi.vercel.app"})
@RequestMapping("/api/v1/auth")
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        try {
            AddToCartResponse addToCartResponse = cartService.addToCartResponse(addToCartRequest);
            return new ResponseEntity<>(new ApiResponse(addToCartResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteFromCart")
    public ResponseEntity<?> deleteFromCart(@RequestBody RemoveFromCartRequest removeFromCartRequest) {
        try {
            RemoveFromCartResponse removeFromCartResponse = cartService.removeFromCartResponse(removeFromCartRequest);
            return new ResponseEntity<>(new ApiResponse(removeFromCartResponse, true), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteCart")
    public ResponseEntity<?> deleteCart(@RequestBody DeleteCartRequest deleteCartRequest) {
        try {
            DeleteCartResponse deleteCartResponse = cartService.deleteCartResponse(deleteCartRequest);
            return new ResponseEntity<>(new ApiResponse(deleteCartResponse, true), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
}
