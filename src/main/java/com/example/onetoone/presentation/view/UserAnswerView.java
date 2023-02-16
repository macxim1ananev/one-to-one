package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserAnswerView {
    String question;
    String answer;
    String responseLevel;
    String comment;
}
