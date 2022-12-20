package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddTechnologyRequest {
    @NotNull
    private String name;
}
