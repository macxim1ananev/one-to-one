package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class QuestionListView {
    Long userId;
    List<QuestionView> questions;
}
