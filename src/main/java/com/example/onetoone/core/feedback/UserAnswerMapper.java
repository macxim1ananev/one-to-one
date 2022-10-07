package com.example.onetoone.core.feedback;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.net.ResponseCache;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {ResponseCache.class}
)
public interface UserAnswerMapper {

    @Mapping(target = "feedbackId", source = "feedbackId")
    UserAnswer toEntity(UserAnswer answer, Long feedbackId);
}
