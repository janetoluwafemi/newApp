package com.example.demo.data.exceptions;

public class InCorrectPasswordException extends RuntimeException {
    public InCorrectPasswordException(String message) {
        super(message);
    }
}
