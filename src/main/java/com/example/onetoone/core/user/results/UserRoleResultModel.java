package com.example.onetoone.core.user.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class UserRoleResultModel {

    Long id;
    String code;
    Set<UserPermissionResultModel> permissions;
}
