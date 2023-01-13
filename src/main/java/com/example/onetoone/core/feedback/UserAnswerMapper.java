package com.example.onetoone.core.feedback;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.technology.TechnologyMapper;
import com.example.onetoone.presentation.request.UserAnswerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {TechnologyMapper.class}
)
public interface UserAnswerMapper {

    @Mapping(target = "feedbackId", source = "feedbackId")
    UserAnswer toEntity(UserAnswer answer, Long feedbackId);

    UserAnswer toUserAnswer(UserAnswerRequest answerRequest);
}
