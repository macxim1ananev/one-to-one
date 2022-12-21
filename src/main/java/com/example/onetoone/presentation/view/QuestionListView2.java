package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class QuestionListView2 {
    Long userId;
    List<QuestionView2> questions;
}
