package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.service.events.UserAnswerEvent;

public interface KafkaGateway {
    void sendUserAnswerEvent(UserAnswerEvent userAnswerEvent);
}
