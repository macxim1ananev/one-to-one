package com.example.onetoone.core.question.entities;

import com.example.onetoone.core.technology.entities.Technology;
import lombok.Data;

@Data
public class Question {
    private Long id;
    private String question;
    private String answer;
    private Technology technology;
    private Long userId;
}
