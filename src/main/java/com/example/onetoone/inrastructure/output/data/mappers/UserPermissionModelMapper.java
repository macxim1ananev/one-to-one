package com.example.onetoone.inrastructure.output.data.mappers;

import com.example.onetoone.core.user.entities.UserPermission;
import com.example.onetoone.inrastructure.output.data.models.UserPermissionModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserPermissionModelMapper {

    UserPermission toEntity(UserPermissionModel userPermissionModel);

    Set<UserPermission> toEntity(Set<UserPermissionModel> userPermissionModels);
}
