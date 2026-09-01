package com.example.inventoryservice.exception;

public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException(Long productId){
        super(String .format("The Inventory not found for %d",productId));
    }

}
