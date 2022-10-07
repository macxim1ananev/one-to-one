package com.example.onetoone.core.question.results;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QuestionResultModel {
    long id;
    String question;
    String answer;
    Long userId;
}
