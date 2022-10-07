package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QuestionView {
    long id;
    String question;
    String answer;
    Long userId;
}
