package com.example.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long productId){
        super(String .format("The Order not found for %d",productId));
    }
}
