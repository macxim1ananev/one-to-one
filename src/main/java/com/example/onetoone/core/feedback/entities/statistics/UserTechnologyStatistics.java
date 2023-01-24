package com.example.onetoone.core.feedback.entities.statistics;

import com.example.onetoone.core.technology.entities.Technology;
import lombok.Data;

@Data
public class UserTechnologyStatistics {
    Long id;
    UserStatistics userStatistics;
    Technology technology;
    int questionCount;
    int totalPoint;

    public void incrementQuestionCount(){
        questionCount+=1;
    }

    public void plusTotalPoint(Integer point){
        totalPoint += point;
    }
}
