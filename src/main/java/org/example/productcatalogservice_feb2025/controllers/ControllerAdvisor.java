package org.example.productcatalogservice_feb2025.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({IllegalArgumentException.class, ArrayIndexOutOfBoundsException.class})
    public ResponseEntity<String> handleExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<> handleException1() {
//        jfoejwo
//    }

}
