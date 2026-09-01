package com.example.inventoryservice.exception;

public class InsufficientStockException extends  RuntimeException{
    public InsufficientStockException(Long id){
        super("Insufficient stock for product");
    }
}
