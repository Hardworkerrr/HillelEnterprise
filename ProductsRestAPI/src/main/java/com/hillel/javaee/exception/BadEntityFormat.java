package com.hillel.javaee.exception;

public class BadEntityFormat extends RuntimeException{
    public BadEntityFormat(String message) {
        super(message);
    }
}
