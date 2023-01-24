package com.example.onetoone.core.feedback.results;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserTechnologyStatisticsResult {
    Long id;
    UserStatisticsResult userStatistics;
    Long technologyId;
    int questionCount;
    int totalPoint;
}
