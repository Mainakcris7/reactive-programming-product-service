package com.practice.reactiveprogramming.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
/*
    Global exception handler class
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle ProductException
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Map<String, String>> handleProductException(ProductException ex){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("statusCode", ex.getStatusCode().toString());
        errMap.put("message", ex.getMessage());

        return ResponseEntity.status(ex.getStatusCode()).body(errMap);
    }
}
