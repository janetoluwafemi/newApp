package com.example.demo.data.services;

import com.example.demo.data.dto.requests.CancelOrderRequest;
import com.example.demo.data.dto.requests.MakeAnOrderRequest;
import com.example.demo.data.dto.responses.CancelOrderResponse;
import com.example.demo.data.dto.responses.MakeAnOrderResponse;

public interface OrderServiceInterface {
    MakeAnOrderResponse makeAnOrderResponse(MakeAnOrderRequest makeAnOrderRequest);
    CancelOrderResponse cancelOrderResponse(CancelOrderRequest cancelOrderRequest);
}
