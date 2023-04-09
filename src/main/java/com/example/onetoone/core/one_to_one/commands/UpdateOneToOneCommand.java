package com.example.onetoone.core.one_to_one.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
@Value
@Builder
public class UpdateOneToOneCommand implements Command {
    Long id;
    Long technologyId;
    Integer levelId;
    LocalDateTime dateTime;
    String comment;
}