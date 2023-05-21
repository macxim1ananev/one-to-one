package com.example.onetoone.core.user.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRegistrationCommand implements Command {
    String telegramUserName;
    Long telegramUserId;
    Long telegramChatId;
    String password;
    String name;
    String surName;
}
