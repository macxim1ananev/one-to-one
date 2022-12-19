package com.example.onetoone.core.feedback.commands;

import com.example.onetoone.core.feedback.entities.ResponseLevel;
import com.example.onetoone.core.question.entities.Question;
import lombok.Data;

@Data
public class UserAnswerRequestModel {
    private Question question;
    private Long feedbackId;
    private ResponseLevel responseLevel;
    private String comment;
}
