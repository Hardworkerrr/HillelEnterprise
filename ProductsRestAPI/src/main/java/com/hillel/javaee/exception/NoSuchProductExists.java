package com.hillel.javaee.exception;

public class NoSuchProductExists extends RuntimeException{
    public NoSuchProductExists(String message) {
        super(message);
    }
}
