package com.example.onetoone.core.feedback;

import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.entities.Feedback;
import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.feedback.results.FeedbackResult;
import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.technology.TechnologyMapper;
import com.example.onetoone.core.technology.entities.Technology;
import com.example.onetoone.core.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {UserAnswerMapper.class, Technology.class, TechnologyMapper.class},
        uses = {UserAnswerMapper.class}
)
public interface FeedbackMapper {
    @Mapping(target = "id", ignore = true)
    Feedback toEntity(CreateFeedbackCommand command, User author, User recipient, OneToOne oneToOne);


    FeedbackResult toResult(Feedback feedback, List<UserAnswer> userAnswerResults);
}
