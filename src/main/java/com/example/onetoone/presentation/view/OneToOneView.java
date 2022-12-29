package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
@Value
@Builder
public class OneToOneView {
    long id;
    long initiatorId;
    Long opponentId;
    TechnologyView technology;
    LocalDateTime dateTime;
    String status;
    String comment;
}
