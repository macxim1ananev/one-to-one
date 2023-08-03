package com.example.onetoone.core.service.events;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.user.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class UserAnswerEvent extends BaseEvent {
    public UserAnswerEvent(List<UserAnswer> entityList, User user) {
        userAnswers = entityList;
        this.user = user;
    }

    private List<UserAnswer> userAnswers;
    private User user;
}
