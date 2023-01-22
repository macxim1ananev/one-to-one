package com.example.onetoone.core.one_to_one.entities;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;
@Getter
public enum OneToOneLevel {
    JUNIOR(1),
    MIDDLE(2),
    SENIOR(3);
    private final Integer id;

    OneToOneLevel(Integer id) {
        this.id = id;
    }

    public static Optional<OneToOneLevel> fromId(Integer id) {
        return Stream.of(OneToOneLevel.values()).filter(c -> c.getId().equals(id)).findFirst();
    }
}
