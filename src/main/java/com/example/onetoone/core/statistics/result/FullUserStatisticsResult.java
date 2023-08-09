package com.example.onetoone.core.statistics.result;

import com.example.onetoone.core.technology.results.TechnologyResult;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FullUserStatisticsResult {
    Long id;
    UserStatisticsResult userStatistics;
    TechnologyResult technology;
    int questionCount;
    int totalPoint;
}
