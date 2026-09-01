package com.example.orderservice.exception;

public class InvalidOrderStatusException extends RuntimeException{
    public InvalidOrderStatusException(Long id){
        super("Cannot cancel delivered Order");
    }
}
