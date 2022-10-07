package com.example.onetoone.inrastructure.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "feedback")
public class FeedbackModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private OneToOneModel oneToOne;
    @ManyToOne
    private UserModel author;
    @ManyToOne
    private UserModel recipient;
    private String message;
}
