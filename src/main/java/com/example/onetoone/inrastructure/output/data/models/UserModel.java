package com.example.onetoone.inrastructure.output.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users", schema = "public")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String telegramUserName;
    private Long telegramUserId;
    private Long telegramChatId;
    private String password;
    private String name;
    private String surName;
    private String status;
    @ManyToOne
    private UserRoleModel role;
}
