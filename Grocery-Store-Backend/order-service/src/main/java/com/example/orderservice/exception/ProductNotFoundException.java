package com.example.orderservice.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long productId){
        super("Product with Id "+productId+" not found.");
    }
}
