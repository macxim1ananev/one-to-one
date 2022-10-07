package com.example.onetoone.presentation.request;

import lombok.Data;

@Data
public class CreateQuestionRequest {
    private String question;
    private String answer;
    private Long userId;
}
