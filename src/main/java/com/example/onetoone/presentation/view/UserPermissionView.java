package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserPermissionView {
    Long id;
    String code;
    String description;
}
