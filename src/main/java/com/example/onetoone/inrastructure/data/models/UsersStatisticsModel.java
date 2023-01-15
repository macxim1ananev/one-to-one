package com.example.onetoone.inrastructure.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_statistics")
public class UsersStatisticsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private UserModel user;
    private Integer totalOneToOneCount;
    private Integer totalQuestionCount;
    private Integer totalPoint;
}
