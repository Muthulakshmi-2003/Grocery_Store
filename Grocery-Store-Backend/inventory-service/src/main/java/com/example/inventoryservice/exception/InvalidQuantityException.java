package com.example.inventoryservice.exception;

public class InvalidQuantityException extends RuntimeException{
    public InvalidQuantityException(Integer quantity){
        super("Quantity must be greter than 0 . Given quantity is : "+quantity);
    }
}
