package com.example.onetoone.core.user;

import com.example.onetoone.core.user.commands.CreateUserCommand;
import com.example.onetoone.core.user.entities.User;
import com.example.onetoone.core.user.entities.UserStatus;
import com.example.onetoone.core.user.results.UserResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {UserStatus.class}
)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", expression = "java(UserStatus.REGISTERED)")
    User toEntity(CreateUserCommand command);

    UserResult toResult(User user);
}
