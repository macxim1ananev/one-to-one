package com.example.onetoone.core.question.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateQuestionCommand implements Command {
    Long id;
    String question;
    String answer;
    Long technologyId;
}
