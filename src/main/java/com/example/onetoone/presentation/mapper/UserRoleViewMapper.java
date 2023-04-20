package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.user.results.UserRoleResultModel;
import com.example.onetoone.presentation.view.UserRoleView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = UserPermissionViewMapper.class
)
public interface UserRoleViewMapper extends BaseViewMapper<UserRoleResultModel, UserRoleView> {

}

