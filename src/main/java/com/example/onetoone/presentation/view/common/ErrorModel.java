package com.example.onetoone.presentation.view.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ErrorModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer errorCode;
    private String errorMessage;

    public ErrorModel(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public ErrorModel(String message) {
        this.errorMessage = message;
    }
}
