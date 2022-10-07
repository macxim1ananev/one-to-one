package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.feedback.entities.ResponseLevel;
import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.presentation.request.UserAnswerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {ResponseLevel.class}
)
public interface FeedbackRequestMapper {
    @Mapping(target = "responseLevel", expression = "java( ResponseLevel.fromId(request.getResponseLevel()).orElse(null))")
    UserAnswer toEntity(UserAnswerRequest request);
}
