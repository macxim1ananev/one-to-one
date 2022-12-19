package com.example.onetoone.core.feedback.results;

import com.example.onetoone.core.question.results.QuestionResultModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserAnswerResult {
    QuestionResultModel question;
    Integer responseLevel;
    String comment;
}
