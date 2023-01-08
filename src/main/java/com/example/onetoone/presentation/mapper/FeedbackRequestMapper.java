package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.presentation.request.UserAnswerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface FeedbackRequestMapper {

    UserAnswer toEntity(UserAnswerRequest request);
}
