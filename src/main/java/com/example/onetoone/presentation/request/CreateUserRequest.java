package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {
    @NotNull
    private String login;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String name;
    private String surName;
}
