package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserStatisticsView {
    Long id;
    UserView user;
    int totalOneToOneCount;
    int totalQuestionCount;
    int totalPoint;
}
