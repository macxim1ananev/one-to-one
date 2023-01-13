package com.example.onetoone.core.user.commands;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserCommand implements Command {
    String login;
    String email;
    String password;
    String name;
    String surName;
}
