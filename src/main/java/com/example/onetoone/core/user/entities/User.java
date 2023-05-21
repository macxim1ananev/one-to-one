package com.example.onetoone.core.user.entities;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String email;
    private String telegramUserName;
    private Long telegramUserId;
    private String password;
    private String name;
    private String surName;
    private UserStatus status;
    private UserRole role;
}
