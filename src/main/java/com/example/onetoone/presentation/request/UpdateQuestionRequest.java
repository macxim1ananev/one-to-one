package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateQuestionRequest {
    @NotBlank
    private String question;
    @NotBlank
    private String answer;
    @NotNull
    private Long userId;
}
