package com.example.onetoone.core.feedback.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetByOneToOneAndRecipientIdCommand implements Command {
    Long oneToOneId;
    Long recipientId;
}
