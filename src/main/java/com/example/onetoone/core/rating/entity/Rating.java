package com.example.onetoone.core.rating.entity;

import lombok.Data;

import java.util.List;
@Data
public class Rating {
    Long id;
    Long userId;
    Integer totalOneToOneCount;
    Integer totalQuestionCount;
    Integer totalAnswerCount;
    Double averageEstimation;
    List<TechnologyAnswer> technologyAnswer;
}
