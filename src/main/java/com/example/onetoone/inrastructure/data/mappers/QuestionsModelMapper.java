package com.example.onetoone.inrastructure.data.mappers;

import com.example.onetoone.core.question.entities.Question;
import com.example.onetoone.inrastructure.data.models.QuestionModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface QuestionsModelMapper {

    QuestionModel toModel(Question entity);

    Question toEntity(QuestionModel model);

    QuestionModel toModel2(Question entity);

    Question toEntity2(QuestionModel save);
}
