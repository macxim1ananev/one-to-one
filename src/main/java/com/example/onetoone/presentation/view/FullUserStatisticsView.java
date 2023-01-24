package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FullUserStatisticsView {
    Long id;
    UserStatisticsView userStatistics;
    TechnologyView technology;
    int questionCount;
    int totalPoint;
}
