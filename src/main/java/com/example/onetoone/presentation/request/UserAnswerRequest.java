package com.example.onetoone.presentation.request;

import com.example.onetoone.presentation.view.QuestionView;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class UserAnswerRequest {
    private QuestionView question;
    @Min(0)
    @Max(5)
    private Integer responseLevel;
    private String comment;
}
