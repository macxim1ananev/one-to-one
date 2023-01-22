package com.example.onetoone.presentation.request;

import com.example.onetoone.core.question.commands.QuestionRequest;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class UserAnswerRequest {
    private QuestionRequest question;
    @Min(1)
    @Max(5)
    private Integer responseLevel;
    private String comment;
}
