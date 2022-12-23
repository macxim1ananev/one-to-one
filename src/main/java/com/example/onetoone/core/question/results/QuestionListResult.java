package com.example.onetoone.core.question.results;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class QuestionListResult {
    Long userId;
    List<QuestionResult> questions;
}
