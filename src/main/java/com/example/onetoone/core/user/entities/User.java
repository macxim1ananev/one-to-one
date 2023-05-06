package com.example.onetoone.core.user.entities;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surName;
    private UserStatus status;
    private UserRole roles;
}
