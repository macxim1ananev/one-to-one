package com.example.onetoone.core.feedback.rating.entity;

import lombok.Data;

@Data
public class UserTechnologyStatistics {
    Long id;
    Long UserStatisticsId;
    Long technologyId;
    int questionCount;
    int totalPoint;

    public void incrementQuestionCount(){
        questionCount+=1;
    }

    public void plusTotalPoint(Integer point){
        totalPoint += point;
    }
}
