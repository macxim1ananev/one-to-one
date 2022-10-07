package com.example.onetoone.core.service.common;


import com.example.onetoone.core.service.command_bus.Command;

import javax.validation.constraints.NotNull;

@FunctionalInterface
public interface Interactor<T extends Command, R> {

    R execute(@NotNull T t);
}
