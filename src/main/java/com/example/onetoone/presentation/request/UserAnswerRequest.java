package com.example.onetoone.presentation.request;

import com.example.onetoone.presentation.view.QuestionView;
import lombok.Data;

@Data
public class UserAnswerRequest {
    private QuestionView question;
    private Integer responseLevel;
}
