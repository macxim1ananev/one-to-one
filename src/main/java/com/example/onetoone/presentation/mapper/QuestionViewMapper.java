package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.question.results.QuestionListResult;
import com.example.onetoone.core.question.results.QuestionResult;
import com.example.onetoone.presentation.view.QuestionListView;
import com.example.onetoone.presentation.view.QuestionView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface QuestionViewMapper {
    QuestionView toView(QuestionResult resultModel);
    QuestionListView toListView(QuestionListResult resultModel);
}
