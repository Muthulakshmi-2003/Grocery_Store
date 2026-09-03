package com.example.authservice.exception;

public class EmailAlreadyExistException  extends RuntimeException{
    public EmailAlreadyExistException(String message){
        super("The given email is alredy registered!!!");

    }
}
