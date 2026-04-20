package com.example.demo.data.services;

import com.example.demo.data.dto.requests.AddToCartRequest;
import com.example.demo.data.dto.requests.DeleteCartRequest;
import com.example.demo.data.dto.requests.RemoveFromCartRequest;
import com.example.demo.data.dto.responses.AddToCartResponse;
import com.example.demo.data.dto.responses.DeleteCartResponse;
import com.example.demo.data.dto.responses.RemoveFromCartResponse;

public interface CartServiceInterface   {
    AddToCartResponse addToCartResponse(AddToCartRequest addToCartRequest);
    DeleteCartResponse deleteCartResponse(DeleteCartRequest editCartItemInCartRequest);
    RemoveFromCartResponse removeFromCartResponse(RemoveFromCartRequest removeFromCartRequest);
}
