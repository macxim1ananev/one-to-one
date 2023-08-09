package com.example.onetoone.core.statistics.result;

import com.example.onetoone.core.technology.results.TechnologyResult;
import com.example.onetoone.core.user.results.UserResult;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserTechnologyStatisticsResult {
    Long id;
    UserResult user;
    TechnologyResult technology;
    int questionCount;
    int totalPoint;
}
