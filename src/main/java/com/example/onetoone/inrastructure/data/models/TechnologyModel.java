package com.example.onetoone.inrastructure.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "technologies")
public class TechnologyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
