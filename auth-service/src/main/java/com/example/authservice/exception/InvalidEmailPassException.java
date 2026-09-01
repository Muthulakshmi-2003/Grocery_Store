package com.example.authservice.exception;

public class InvalidEmailPassException extends RuntimeException {
    public InvalidEmailPassException(){
        super("Invalid email or password!!");
    }
}
