package com.example.onetoone.core.feedback.results;

import com.example.onetoone.core.question.results.QuestionResult;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserAnswerResult {
    QuestionResult question;
    Integer responseLevel;
    String comment;
}
