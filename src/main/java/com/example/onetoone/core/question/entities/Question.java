package com.example.onetoone.core.question.entities;

import lombok.Data;

@Data
public class Question {
    private Long id;
    private String question;
    private String answer;
    private Long userId;
}
