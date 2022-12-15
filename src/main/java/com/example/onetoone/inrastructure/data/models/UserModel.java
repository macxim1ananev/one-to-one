package com.example.onetoone.inrastructure.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String email;
    private String password;
}
