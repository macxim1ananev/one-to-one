package com.example.onetoone.core.feedback.commands;

import com.example.onetoone.core.service.command_bus.Command;
import com.example.onetoone.presentation.request.UserAnswerRequest;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateFeedbackCommand implements Command {
    long oneToOneId;
    long authorId;
    long recipientId;
    List<UserAnswerRequest> questions;
    String message;
}
