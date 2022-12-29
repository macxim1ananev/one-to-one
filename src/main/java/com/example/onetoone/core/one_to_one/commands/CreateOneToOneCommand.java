package com.example.onetoone.core.one_to_one.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class CreateOneToOneCommand implements Command {
    long initiatorId;
    Long technologyId;
    LocalDateTime dateTime;
    String comment;
}
