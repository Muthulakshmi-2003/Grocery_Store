package com.example.orderservice.exception;

public class ConfirmedOrderCannotCancellException extends RuntimeException{
    public ConfirmedOrderCannotCancellException(){
        super("Confirmed Order Cannot be Cancelled!!");
    }
}
