package com.example.orderservice.exception;

public class ProductInactiveException extends RuntimeException{
    public ProductInactiveException(Long productId){
        super("Product with id "+productId+" is inactive and cannot be ordered");
    }
}
