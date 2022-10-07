package com.example.onetoone.inrastructure.data.mappers;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.inrastructure.data.models.UserAnswerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserAnswerModelMapper {
    @Mapping(target = "responseLevel", expression = "java(ResponseLevel.fromId(model.getResponseLevelId()).orElse(null))")
    UserAnswer toEntity(UserAnswerModel model);

    @Mapping(target = "responseLevelId", source = "responseLevel.id")
    UserAnswerModel toModel(UserAnswer userAnswer);
}
