package com.example.onetoone.core.one_to_one.entities;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum FeedbackStatus {
    NO_WRITE(1),
    WRITE(2);
    private final Integer id;

    FeedbackStatus(Integer id) {
        this.id = id;
    }

    public static Optional<FeedbackStatus> fromId(Integer id) {
        return Stream.of(FeedbackStatus.values()).filter(c -> c.getId().equals(id)).findFirst();
    }
}
