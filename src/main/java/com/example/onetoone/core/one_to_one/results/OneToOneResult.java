package com.example.onetoone.core.one_to_one.results;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class OneToOneResult {
    long id;
    long initiatorId;
    Long opponentId;
    String programmingLanguage;
    LocalDateTime dateTime;
    String status;
}
