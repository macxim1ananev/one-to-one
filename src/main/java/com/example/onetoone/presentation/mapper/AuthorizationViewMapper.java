package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.auth.result.UserAuthorizationResult;
import com.example.onetoone.presentation.view.JwtTokenView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthorizationViewMapper {
    JwtTokenView toView(UserAuthorizationResult result);
}
