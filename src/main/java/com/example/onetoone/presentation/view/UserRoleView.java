package com.example.onetoone.presentation.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class UserRoleView {
    Long id;
    String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Set<UserPermissionView> permissions;
}
