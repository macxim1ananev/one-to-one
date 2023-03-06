package com.example.onetoone.inrastructure.output.data.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "one_to_one")
public class OneToOneModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private UserModel initiator;
    @OneToOne
    private UserModel opponent;
    @OneToOne
    private TechnologyModel technology;
    private LocalDateTime dateTime;
    private Integer levelId;
    private Integer statusId;
    private Integer initiatorFeedbackStatusId;
    private Integer opponentFeedbackStatusId;
    private String comment;
}
