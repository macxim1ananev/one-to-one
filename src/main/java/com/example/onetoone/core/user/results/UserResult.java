package com.example.onetoone.core.user.results;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResult {
    Long id;
    String login;
    String email;
    String password;
    String name;
    String surName;
    String status;
}
