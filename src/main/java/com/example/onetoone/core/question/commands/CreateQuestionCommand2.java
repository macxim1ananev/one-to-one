package com.example.onetoone.core.question.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateQuestionCommand2 implements Command {
    Long userId;
    List<QuestionRequest> questions;

}
