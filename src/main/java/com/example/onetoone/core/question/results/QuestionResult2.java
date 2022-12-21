package com.example.onetoone.core.question.results;

import com.example.onetoone.core.technology.results.TechnologyResult;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QuestionResult2 {
    Long id;
    String question;
    String answer;
    TechnologyResult technology;
    Long userId;
}
