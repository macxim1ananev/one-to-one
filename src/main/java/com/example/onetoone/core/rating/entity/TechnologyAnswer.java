package com.example.onetoone.core.rating.entity;

import com.example.onetoone.core.technology.entities.Technology;
import lombok.Data;

@Data
public class TechnologyAnswer {
    Long userId;
    Technology technology;
    Integer questionCount;
    Integer answerCount;
    Double averageEstimation;
}
