package com.example.inventoryservice.exception;

public class InventoryAlreadyExists extends RuntimeException {

    public InventoryAlreadyExists(Long id) {
        super(String.format("The Inventory already exists for Product id  %d ", id));
    }
}
