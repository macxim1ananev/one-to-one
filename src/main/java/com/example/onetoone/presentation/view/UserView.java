package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserView {
    Long id;
    String email;
    String name;
    String surName;
    String status;
}
