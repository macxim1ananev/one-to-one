package com.example.onetoone.core.question.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class QuestionRequest {
    @NotNull
    @NotBlank
    private String question;
    @NotNull
    @NotBlank
    private String answer;
    @NotNull
    private Long technologyId;
    @NotNull
    private Long userId;
}
