package com.example.onetoone.inrastructure.data.models;

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
    private Integer programmingLanguageId;
    private LocalDateTime dateTime;
    private Integer statusId;
}
