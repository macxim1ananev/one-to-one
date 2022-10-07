package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class FeedbackView {
    Long id;
    long oneToOneId;
    long authorId;
    long recipientId;
    List<UserAnswerView> questions;
    String message;
}
