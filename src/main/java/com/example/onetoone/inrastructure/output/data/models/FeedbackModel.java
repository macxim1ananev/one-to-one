package com.example.onetoone.inrastructure.output.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "feedback", schema = "public")
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
