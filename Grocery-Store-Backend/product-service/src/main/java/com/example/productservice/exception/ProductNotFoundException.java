package com.example.productservice.exception;

public class ProductNotFoundException  extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("The give Product is not found with id : "+ id);
    }
}
