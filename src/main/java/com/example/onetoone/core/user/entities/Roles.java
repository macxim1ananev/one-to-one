package com.example.onetoone.core.user.entities;

import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Roles implements GrantedAuthority {
    @FieldNameConstants.Include USER,
    @FieldNameConstants.Include ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
