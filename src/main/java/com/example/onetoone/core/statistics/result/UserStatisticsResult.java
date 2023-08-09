package com.example.onetoone.core.statistics.result;

import com.example.onetoone.core.user.results.UserResult;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserStatisticsResult {
    Long id;
    UserResult user;
    int totalOneToOneCount;
    int totalQuestionCount;
    int totalPoint;
}
