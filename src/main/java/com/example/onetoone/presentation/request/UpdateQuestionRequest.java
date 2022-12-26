package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateQuestionRequest {
    @NotNull
    @NotBlank
    private String question;
    @NotNull
    @NotBlank
    private String answer;
    @NotNull
    @NotBlank
    private Long userId;
}
