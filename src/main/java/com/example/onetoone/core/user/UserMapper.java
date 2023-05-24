package com.example.onetoone.core.user;

import com.example.onetoone.core.user.commands.CreateUserCommand;
import com.example.onetoone.core.user.commands.PreRegistrationUserCommand;
import com.example.onetoone.core.user.commands.UserRegistrationCommand;
import com.example.onetoone.core.user.entities.User;
import com.example.onetoone.core.user.entities.UserStatus;
import com.example.onetoone.core.user.results.PreRegistrationUserResult;
import com.example.onetoone.core.user.results.UserRegistrationResult;
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
    UserRegistrationResult toUserRegistrationResult(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", expression = "java(UserStatus.REGISTERED)")
    User toEntity(PreRegistrationUserCommand command);

    PreRegistrationUserResult toPreRegistrationUserResult(User user);
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", expression = "java(UserStatus.REGISTERED)")
    User toEntity(UserRegistrationCommand command);
}
