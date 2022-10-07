package com.example.onetoone.presentation;

import com.example.onetoone.core.service.error.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ServiceException.class)
    public Object handleCoreExceptions(
            ServiceException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
