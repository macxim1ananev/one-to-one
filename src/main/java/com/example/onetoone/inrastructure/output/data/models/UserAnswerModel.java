package com.example.onetoone.inrastructure.output.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_answer")
public class UserAnswerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long feedbackId;
    @OneToOne
    private QuestionModel question;
    private Integer responseLevel;
    private String comment;
}
