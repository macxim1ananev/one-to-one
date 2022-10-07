package com.example.onetoone.core.feedback.commands;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateFeedbackCommand implements Command {
    long oneToOneId;
    long authorId;
    long recipientId;
    List<UserAnswer> questions;
    String message;
}
