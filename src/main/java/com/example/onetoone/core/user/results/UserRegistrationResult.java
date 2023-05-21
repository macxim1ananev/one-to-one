package com.example.onetoone.core.user.results;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRegistrationResult {
    Long id;
    String email;
    String telegramUserName;
    Long telegramUserId;
    String name;
    String surName;
    String status;
}
