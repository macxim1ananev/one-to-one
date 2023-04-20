package com.example.onetoone.core.user.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUserPermissionByCodeCommand implements Command {

    String code;
}
