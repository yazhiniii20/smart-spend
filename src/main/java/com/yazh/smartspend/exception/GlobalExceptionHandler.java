package com.yazh.smartspend.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice //Listen for exceptions from all controllers. Instead of writing try-catch everywhere, one central place handles errors
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class) //Whenever validation fails run this method
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));
        return errors;
    }
}
