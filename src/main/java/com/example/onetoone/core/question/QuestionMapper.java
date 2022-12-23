package com.example.onetoone.core.question;

import com.example.onetoone.core.question.commands.QuestionRequest;
import com.example.onetoone.core.question.commands.UpdateQuestionCommand;
import com.example.onetoone.core.question.entities.Question;
import com.example.onetoone.core.question.results.QuestionResult;
import com.example.onetoone.core.technology.entities.Technology;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface QuestionMapper {
    @Mapping(target = "id", ignore = true)
    Question update(@MappingTarget Question question, UpdateQuestionCommand command);

    @Mapping(target = "technology", source = "technology")
    @Mapping(target = "id", ignore = true)
    Question toEntity(QuestionRequest questionRequest, Technology technology);

    QuestionResult toResult(Question question);
}
