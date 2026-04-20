package com.example.demo.data.exceptions;

public class CartDoesNotExistException extends RuntimeException {
    public CartDoesNotExistException(String message) {
        super(message);
    }
}
