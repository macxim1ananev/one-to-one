package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.feedback.commands.UserAnswerRequestModel;
import com.example.onetoone.core.feedback.results.FeedbackResult;
import com.example.onetoone.presentation.view.FeedbackView;
import com.example.onetoone.presentation.view.UserAnswerView;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface FeedbackViewMapper {

    @Mapping(target = "oneToOneId", source = "oneToOne.id")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "recipientId", source = "recipient.id")
    @Mapping(target = "questions", source = "userAnswerResults", qualifiedByName = "setQuestions")
    FeedbackView toView (FeedbackResult feedbackResult);


    @Mapping(target = "question", source = "question.question")
    @Mapping(target = "responseLevel", source = "responseLevel")
    UserAnswerView toUserAnswerView (UserAnswerRequestModel requestModel);

    @Named("setQuestions")
    default List<UserAnswerView> setQuestions(List<UserAnswerRequestModel> resultList){
        return resultList.stream()
                .map(en -> toUserAnswerView(en))
                .collect(Collectors.toList());
    }


}
