package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.user.results.PreRegistrationUserResult;
import com.example.onetoone.core.user.results.UserResult;
import com.example.onetoone.presentation.view.UserView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserViewMapper {
    UserView toView(UserResult user);

    UserView toViewFromPreRegistrar(PreRegistrationUserResult userResult);
}
