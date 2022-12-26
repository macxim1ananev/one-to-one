package com.example.onetoone.presentation.request;

import com.example.onetoone.core.question.commands.QuestionRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateQuestionRequest {
    @NotNull
    @NotBlank
    private List<QuestionRequest> questions;
}
