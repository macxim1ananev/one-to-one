package com.example.onetoone.core.feedback.entities;

import lombok.Getter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum ResponseLevel {
    BAD(1),
    SATISFACTORY(2),
    EXCELLENT(3),
    NOT_ASKED(4);


    private final Integer id;

    ResponseLevel(Integer id) {
        this.id = id;
    }

    public static Optional<ResponseLevel> fromString(String code) {
        return Stream.of(ResponseLevel.values()).filter(c -> Objects.equals(code, c.name())).findFirst();
    }

    public static Optional<ResponseLevel> fromId(Integer id) {
        return Stream.of(ResponseLevel.values()).filter(c -> c.getId().equals(id)).findFirst();
    }
}
