package com.Electro.Exception;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(){
        super("Resource not fount !!");



}
    public ResourceNotFoundException(String message) {

        super(message);
    }
}
