package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserTechnologyStatisticsView {
    Long id;
    UserView user;
    TechnologyView technology;
    int questionCount;
    int totalPoint;
}
