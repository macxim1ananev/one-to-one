package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.user.results.UserPermissionResultModel;
import com.example.onetoone.presentation.view.UserPermissionView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserPermissionViewMapper extends BaseViewMapper<UserPermissionResultModel, UserPermissionView> {

}

