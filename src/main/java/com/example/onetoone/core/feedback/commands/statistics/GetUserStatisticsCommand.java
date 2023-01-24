package com.example.onetoone.core.feedback.commands.statistics;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUserStatisticsCommand implements Command {
    Long id;
}
