package com.example.authservice.exception;

public class EmailAlreadyExistException  extends RuntimeException{
    public EmailAlreadyExistException(){
        super("The given email is alredy registered!!!");

    }
}
