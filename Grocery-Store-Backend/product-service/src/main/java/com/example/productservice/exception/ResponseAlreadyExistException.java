package com.example.productservice.exception;

public class ResponseAlreadyExistException extends RuntimeException{

    public ResponseAlreadyExistException(String name){
        super(String.format(" The Given name %s is already exist.", name));

    }
}
