package com.example.onetoone.core.feedback.results;

import com.example.onetoone.core.technology.results.TechnologyResult;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserTechnologyStatisticsResult {
    Long id;
    UserStatisticsResult userStatistics;
    TechnologyResult technology;
    int questionCount;
    int totalPoint;
}
