package com.example.demo.data.controllers;

import com.example.demo.data.dto.requests.CancelOrderRequest;
import com.example.demo.data.dto.requests.MakeAnOrderRequest;
import com.example.demo.data.dto.responses.ApiResponse;
import com.example.demo.data.dto.responses.CancelOrderResponse;
import com.example.demo.data.dto.responses.MakeAnOrderResponse;
import com.example.demo.data.services.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://email-app-chi.vercel.app"})
@RequestMapping("/api/v1/auth")
public class OrderController {
    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }
//    public ResponseEntity<?> makeOrder(@RequestParam String token, Long productId) {
    @PostMapping("/makeOrder")
    public ResponseEntity<?> makeOrder(@RequestParam(required = false) String token, @RequestParam Long productId,
                                       @RequestBody MakeAnOrderRequest makeAnOrderRequest) {
        try {
            MakeAnOrderResponse makeAnOrderResponse = orderService.makeAnOrderResponse(token, productId, makeAnOrderRequest);
            return new ResponseEntity<>(new ApiResponse(makeAnOrderResponse, true), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/cancelOrder")
    public ResponseEntity<?> cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest) {
        try {
            CancelOrderResponse cancelOrderResponse = orderService.cancelOrderResponse(cancelOrderRequest);
            return new ResponseEntity<>(new ApiResponse(cancelOrderResponse, true), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(new ApiResponse(error, false), HttpStatus.BAD_REQUEST);
        }
    }
}
