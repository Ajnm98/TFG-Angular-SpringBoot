package com.example.springboottfg.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception{

    private final int code;

    public NotFoundException(final int code, final String message){
        super(message);
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
