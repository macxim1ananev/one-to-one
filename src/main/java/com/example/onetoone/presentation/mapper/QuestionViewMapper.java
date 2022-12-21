package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.question.results.QuestionResultModel;
import com.example.onetoone.core.question.results.QuestionListResult2;
import com.example.onetoone.presentation.view.QuestionListView2;
import com.example.onetoone.presentation.view.QuestionView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface QuestionViewMapper {
    QuestionView toView(QuestionResultModel resultModel);
    QuestionListView2 toView2(QuestionListResult2 resultModel);
}
