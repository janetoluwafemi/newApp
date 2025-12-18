package com.example.demo.data.exceptions;

public class OTPIsEmptyException extends RuntimeException {
    public OTPIsEmptyException(String message) {
        super(message);
    }
}
