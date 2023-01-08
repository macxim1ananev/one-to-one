package com.example.onetoone.core.feedback.rating.entity;

import com.example.onetoone.core.user.entities.User;
import lombok.Data;

import java.util.List;
@Data
public class UserStatistics {
    Long id;
    User user;
    Integer totalOneToOneCount;
    Integer totalQuestionCount;
    Integer totalPoint;
    List<UserTechnologyStatistics> technologyStatistics;

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
