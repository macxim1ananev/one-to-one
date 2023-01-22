package com.example.onetoone.core.feedback.rating.entity;

import com.example.onetoone.core.user.entities.User;
import lombok.Data;

@Data
public class UserStatistics {
    Long id;
    User user;
    int totalOneToOneCount;
    int totalQuestionCount;
    int totalPoint;

    public void plusTotalPoint(Integer point){
        totalPoint += point;
    }

    public void incrementOneToOneCount(){
        totalOneToOneCount+=1;
    }

    public void incrementTotalQuestionCount(Integer count){
        totalQuestionCount+=count;
    }
}
