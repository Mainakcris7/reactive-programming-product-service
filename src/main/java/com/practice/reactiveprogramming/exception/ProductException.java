package com.practice.reactiveprogramming.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/* Custom exception class*/

@Setter
@Getter
@NoArgsConstructor
public class ProductException extends RuntimeException{
    private HttpStatus statusCode;

    public ProductException(HttpStatus statusCode, String message){
        super(message);
        this.statusCode = statusCode;
    }
}
