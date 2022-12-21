package com.example.onetoone.presentation.request;

import com.example.onetoone.core.question.commands.QuestionRequest;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionRequest2 {
    private List<QuestionRequest> questions;
}
