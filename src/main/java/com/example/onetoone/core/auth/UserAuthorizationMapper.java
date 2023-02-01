package com.example.onetoone.core.auth;

import com.example.onetoone.core.auth.result.RefreshJwtTokenResult;
import com.example.onetoone.core.auth.result.UserAuthorizationResult;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserAuthorizationMapper {
    UserAuthorizationResult toResult(String jwtToken, String refreshToken);
    RefreshJwtTokenResult toRefreshTokenResult(String jwtToken, String refreshToken);
}
