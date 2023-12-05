package com.Electro.Exception;

public class BadApiRequest extends RuntimeException {

    private final String message;

    public BadApiRequest(String message) {
        super(message);
        this.message = message;
    }
}


