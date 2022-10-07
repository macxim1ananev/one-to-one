package com.example.onetoone.core.question;

import com.example.onetoone.core.question.commands.CreateQuestionCommand;
import com.example.onetoone.core.question.entities.Question;
import com.example.onetoone.core.question.results.QuestionResultModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface QuestionMapper {
    @Mapping(target = "id", ignore = true)
    Question toEntity(CreateQuestionCommand command);

    QuestionResultModel toResult(Question model);
}
