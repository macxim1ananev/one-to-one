package com.example.onetoone.core.technology.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddTechnologyCommand implements Command {
    String name;
}
