package com.example.demo.data.exceptions;

public class NoPaymentWasMadeWithThisEmailException extends RuntimeException {
    public NoPaymentWasMadeWithThisEmailException(String message) {
        super(message);
    }
}
