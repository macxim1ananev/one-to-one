package com.example.onetoone.presentation;

import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.presentation.view.common.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ServiceException.class)
    public Object handleCoreExceptions(
            ServiceException e) {
        log.error("Service error {}", e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorModel(e.getErrorCode(), e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidationExceptions(
            MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors()
                .stream().map(error -> error instanceof FieldError ?
                        ((FieldError) error).getField() + ": " + error.getDefaultMessage() :
                        error.getDefaultMessage())
                .distinct().collect(Collectors.joining(", ", "", ""));
        log.error("Validation request error {}", message);
        return ResponseEntity.badRequest().body(new ErrorModel(message));
    }
}
