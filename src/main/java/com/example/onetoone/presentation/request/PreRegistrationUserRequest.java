package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PreRegistrationUserRequest {
    @NotNull
    private String email;
}