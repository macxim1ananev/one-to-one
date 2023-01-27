package com.example.onetoone.core.auth.command;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshJwtTokenCommand implements Command {
    String refreshToken;
}
