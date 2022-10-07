package com.example.onetoone.core.question.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateQuestionCommand implements Command {
    String question;
    String answer;
    long userId;
}
