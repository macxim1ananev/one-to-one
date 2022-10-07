package com.example.onetoone.core.service.command_bus;

public interface CommandBus {

    <C extends Command, R> R execute(C command);
}