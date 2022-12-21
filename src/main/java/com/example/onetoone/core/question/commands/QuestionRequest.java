package com.example.onetoone.core.question.commands;

import lombok.Data;

@Data
public class QuestionRequest {
    private String question;
    private String answer;
    private Long technologyId;
    private Long userId;
}
