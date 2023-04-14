package com.example.onetoone.core.user.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRole {

    private Long id;
    private String code;
}
