package com.example.authservice.constant;

import com.example.authservice.exception.EmailAlreadyExistException;

public final class MessageConstant {
    public static final String Email_Already_Exists = EmailAlreadyExistException.class.getSimpleName();
    private MessageConstant() {
    }
}
