package com.example.demo.data.services;


import com.example.demo.data.dto.requests.CancelOrderRequest;
import com.example.demo.data.dto.requests.MakeAnOrderRequest;
import com.example.demo.data.dto.responses.CancelOrderResponse;
import com.example.demo.data.dto.responses.MakeAnOrderResponse;
import com.example.demo.data.exceptions.OrderDoesNotExistException;
import com.example.demo.data.exceptions.ProductDoesNotExistException;
import com.example.demo.data.exceptions.UserNotFoundException;
import com.example.demo.data.models.Order;
import com.example.demo.data.models.Product;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Service
    public class OrderServiceImpl implements OrderServiceInterface{
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MailService mailService;


    @Override
    public MakeAnOrderResponse makeAnOrderResponse(String token, Long productId, MakeAnOrderRequest makeAnOrderRequest) {
//        User existingUser = userRepo.findUserByToken(token)
//                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        User existingUser = userRepo.findUserByToken(token)
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail(makeAnOrderRequest.getEmail());
                    if (userRepo.findUserByEmail(makeAnOrderRequest.getEmail()).isEmpty()) {
                        return userRepo.save(user);
                    }
                    return user;
                });
        Optional<Product> product = productRepo.findProductById(productId);
        String email = "oluwafemijanet85@gmail.com";
        if (product.isPresent()) {
            Order order = new Order();
            order.setQuantity(makeAnOrderRequest.getQuantity());
            order.setUser(existingUser);
            Product existingProduct = product.get();
            List<Product> products = new ArrayList<>();
            products.add(product.get());
            order.setProduct(products);
            String userEmail = java.util.Optional.ofNullable(existingUser.getEmail())
                    .orElse(makeAnOrderRequest.getEmail());

            String body = ("I want to place an order for " + existingProduct.getProductName() + " (quantity: " + makeAnOrderRequest.getQuantity() + ").");
            mailService.sendEmailToUser(
                    email,
                    userEmail,
                    body
            );
            orderRepo.save(order);
            MakeAnOrderResponse makeAnOrderResponse = new MakeAnOrderResponse();
            makeAnOrderResponse.setProduct(product.get());
            makeAnOrderResponse.setOrderId(order.getId());
            makeAnOrderResponse.setMessage("Order made successfully, An Email Was Sent To The Company");
            return makeAnOrderResponse;
        }
        throw new ProductDoesNotExistException("Product does not exist");
    }

    @Override
    public CancelOrderResponse cancelOrderResponse(CancelOrderRequest cancelOrderRequest) {
        Optional<User> user = userRepo.findUserById(cancelOrderRequest.getUserId());
        Optional<Order> existingOrder = orderRepo.findOrderById(cancelOrderRequest.getOrderId());
        if (user.isPresent()) {
            if (existingOrder.isPresent()) {
                Order order = existingOrder.get();
                mailService.sendEmailToUser(
                        cancelOrderRequest.getEmail(),
                        String.valueOf(cancelOrderRequest.getOrderId()),
                        "I cancelled the order"
                );
                orderRepo.delete(order);
                CancelOrderResponse cancelOrderResponse = new CancelOrderResponse();
                cancelOrderResponse.setMessage("Order cancelled successfully");
                return cancelOrderResponse;
            }
            throw new OrderDoesNotExistException("Order does not exist");
        }
        throw new UserNotFoundException("User not found");
    }
}
