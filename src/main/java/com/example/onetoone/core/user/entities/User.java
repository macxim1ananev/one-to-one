package com.example.onetoone.core.user.entities;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String login;
    private String email;
    private String password;
}
