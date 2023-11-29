package com.Electro.Exception;


import com.Electro.Dto.ApiResponseMassage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMassage> resourceNotFoundException(ResourceNotFoundException ex){

        ApiResponseMassage api = ApiResponseMassage.builder().message(ex.getMessage()).status(false).statusCode(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(api,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> mathodArgumentNotValidHanlder(MethodArgumentNotValidException ex) {

        Map<String,String>map=new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(e-> map.put(e.getField(),e.getDefaultMessage()));

        return new ResponseEntity<Map<String,String>>(map,HttpStatus.NOT_FOUND);

    }

}
