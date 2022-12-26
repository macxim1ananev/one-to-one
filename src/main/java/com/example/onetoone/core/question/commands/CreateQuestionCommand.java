package com.example.onetoone.core.question.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Builder
public class CreateQuestionCommand implements Command {
    @NotNull
    @NotBlank
    Long userId;
    @NotNull
    @NotBlank
    List<QuestionRequest> questions;

}
