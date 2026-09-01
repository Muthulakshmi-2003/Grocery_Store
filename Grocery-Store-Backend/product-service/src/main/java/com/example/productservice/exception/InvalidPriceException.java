package com.example.productservice.exception;

import java.math.BigDecimal;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException(BigDecimal price){
        super("Price must be greter than 0 . Given price is : "+price);
    }
}
