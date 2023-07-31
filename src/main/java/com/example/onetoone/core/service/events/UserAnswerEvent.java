package com.example.onetoone.core.service.events;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import lombok.Data;

import java.util.List;

@Data
public class UserAnswerEvent extends BaseEvent {
    public UserAnswerEvent(List<UserAnswer> entityList) {
        userAnswers = entityList;
    }

    private List<UserAnswer> userAnswers;
}
