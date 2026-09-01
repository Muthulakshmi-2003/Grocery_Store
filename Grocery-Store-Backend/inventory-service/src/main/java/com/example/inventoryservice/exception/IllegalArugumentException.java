package com.example.inventoryservice.exception;

public class IllegalArugumentException extends RuntimeException{
    public IllegalArugumentException(){
        super ("Reserved quantity is insufficient!!!");
    }
}
