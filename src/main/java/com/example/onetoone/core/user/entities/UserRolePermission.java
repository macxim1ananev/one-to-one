package com.example.onetoone.core.user.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRolePermission {

    private Long userRoleId;
    private Long userPermissionId;
}
