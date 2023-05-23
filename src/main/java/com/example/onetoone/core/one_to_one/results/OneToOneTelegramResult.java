package com.example.onetoone.core.one_to_one.results;

import com.example.onetoone.core.technology.results.TechnologyResult;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
@Value
@Builder
public class OneToOneTelegramResult {
    long id;
    long initiatorId;
    Long opponentId;
    TechnologyResult technology;
    String level;
    LocalDateTime dateTime;
    String status;
    String initiatorFeedback;
    String opponentFeedback;
    String comment;
    String initiatorUserName;
    String opponentUserName;
}
