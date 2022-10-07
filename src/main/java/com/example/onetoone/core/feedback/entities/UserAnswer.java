package com.example.onetoone.core.feedback.entities;

import com.example.onetoone.core.question.entities.Question;
import lombok.Data;

@Data
public class UserAnswer {
    private Question question;
    private Long feedbackId;
    private ResponseLevel responseLevel;
}
