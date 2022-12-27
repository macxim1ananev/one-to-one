package com.example.onetoone.presentation.request;

import com.example.onetoone.core.question.commands.QuestionRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateQuestionRequest {
    @NotNull
    private List<QuestionRequest> questions;
}
