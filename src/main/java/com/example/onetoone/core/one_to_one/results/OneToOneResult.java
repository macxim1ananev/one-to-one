package com.example.onetoone.core.one_to_one.results;

import com.example.onetoone.core.technology.results.TechnologyResult;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class OneToOneResult {
    long id;
    long initiatorId;
    Long opponentId;
    TechnologyResult technology;
    LocalDateTime dateTime;
    String status;
    String comment;
}
