package com.example.onetoone.core.user;

import com.example.onetoone.core.service.interfaces.UserPermissions;
import com.example.onetoone.core.user.entities.UserRole;
import com.example.onetoone.core.user.results.UserPermissionResultModel;
import com.example.onetoone.core.user.results.UserRoleResultModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        builder = @Builder(disableBuilder = true)
)
public abstract class UserRoleMapper {

    @Autowired
    protected UserPermissions userPermissions;

    @Autowired
    protected UserPermissionsMapper userPermissionsMapper;

    @Mapping(target = "permissions", source = "code")
    public abstract UserRoleResultModel toModel(UserRole userRole);

    public Set<UserPermissionResultModel> getPermissions(String userRoleCode) {
        return userPermissionsMapper.toModel(
                userPermissions.findByRoleCode(userRoleCode)
        );
    }

}
