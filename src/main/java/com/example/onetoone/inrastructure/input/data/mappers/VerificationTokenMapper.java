package com.example.onetoone.inrastructure.input.data.mappers;

import com.example.onetoone.core.user.entities.VerificationToken;
import com.example.onetoone.inrastructure.input.data.models.VerificationTokenModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {Timestamp.class})
public interface VerificationTokenMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "token", target = "token")
    VerificationToken toVerificationToken(Long userId, String token);

    VerificationTokenModel toModel(VerificationToken verificationToken);

    VerificationToken toEntity(VerificationTokenModel tokenModel);
}
