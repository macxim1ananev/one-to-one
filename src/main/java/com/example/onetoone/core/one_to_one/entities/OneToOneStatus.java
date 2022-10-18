package com.example.onetoone.core.one_to_one.entities;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum OneToOneStatus {
    CLOSED(1),
    ACCEPT(2),
    OPEN(3);
    private final Integer id;

    OneToOneStatus(Integer id) {
        this.id = id;
    }

    public static Optional<OneToOneStatus> fromId(Integer id) {
        return Stream.of(OneToOneStatus.values()).filter(c -> c.getId().equals(id)).findFirst();
    }
}
