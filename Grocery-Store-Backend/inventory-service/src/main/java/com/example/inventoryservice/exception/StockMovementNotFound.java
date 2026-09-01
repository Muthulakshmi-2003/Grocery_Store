package com.example.inventoryservice.exception;

public class StockMovementNotFound extends RuntimeException{
    public StockMovementNotFound(Long productId){
        super(String .format("The StockMovement not found for %d",productId));

    }
}
