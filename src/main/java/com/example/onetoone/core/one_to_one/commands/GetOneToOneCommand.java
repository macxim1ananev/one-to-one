package com.example.onetoone.core.one_to_one.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetOneToOneCommand implements Command {
    Long id;
}
