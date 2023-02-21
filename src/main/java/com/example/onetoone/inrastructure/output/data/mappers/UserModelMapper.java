package com.example.onetoone.inrastructure.output.data.mappers;

import com.example.onetoone.core.user.entities.User;
import com.example.onetoone.inrastructure.output.data.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserModelMapper {
    UserModel toModel(User entity);
    User toEntity(UserModel model);
}