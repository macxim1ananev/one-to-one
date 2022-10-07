package com.example.onetoone.core.one_to_one.entities;

import lombok.Getter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
@Getter
public enum ProgrammingLanguage {
    JAVA(1);

    private final Integer id;

    ProgrammingLanguage(Integer id) {
        this.id = id;
    }

    public static Optional<ProgrammingLanguage> fromString(String code) {
        return Stream.of(ProgrammingLanguage.values()).filter(c -> Objects.equals(code, c.name())).findFirst();
    }

    public static Optional<ProgrammingLanguage> fromId(Integer id) {
        return Stream.of(ProgrammingLanguage.values()).filter(c -> c.getId().equals(id)).findFirst();
    }
}
