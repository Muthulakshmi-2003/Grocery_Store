package com.example.orderservice.exception;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(Long id){
        super("Insufficient stock for product!!");
    }
}
