package com.example.productservice.exception;

public class DuplicateskuException extends RuntimeException {
    public DuplicateskuException(String sku){
        super(String.format("Product with SKU '%s' already exists", sku));
    }
}
