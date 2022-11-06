package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.feedback.entities.UserAnswer;

import java.util.List;

public interface UserAnswers {
    UserAnswer put(UserAnswer userAnswer);

    List<UserAnswer> getAllByFeedbackId(Long feedbackId);
}
