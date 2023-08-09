package com.example.onetoone.core.statistics.entity;

import com.example.onetoone.core.user.entities.User;
import lombok.Data;

@Data
public class UserStatistics {
    private Long id;
    private User user;
    private int totalOneToOneCount;
    private int totalQuestionCount;
    private int totalPoint;

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
