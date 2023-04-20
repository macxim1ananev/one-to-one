package com.example.onetoone.core.user;

import com.example.onetoone.core.user.entities.UserPermission;
import com.example.onetoone.core.user.results.UserPermissionResultModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserPermissionsMapper {

    UserPermissionResultModel toModel(UserPermission userPermission);

    List<UserPermissionResultModel> toModel(List<UserPermission> userPermissions);

    Set<UserPermissionResultModel> toModel(Set<UserPermission> userPermissions);
}
