package com.example.onetoone.inrastructure.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_technology_statistics")
public class UserTechnologyStatisticsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UsersStatisticsModel UserStatistics;
    private Long technologyId;
    private Integer totalPoint;
    private Integer questionCount;
}
