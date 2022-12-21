package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QuestionView2 {
    Long id;
    String question;
    String answer;
    TechnologyView technology;
    Long userId;
}
