package com.example.onetoone.core.feedback.interactors;

import com.example.onetoone.core.feedback.FeedbackMapper;
import com.example.onetoone.core.feedback.UserAnswerMapper;
import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.entities.Feedback;
import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.feedback.results.FeedbackResult;
import com.example.onetoone.core.one_to_one.entities.FeedbackStatus;
import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.events.UserAnswerEvent;
import com.example.onetoone.core.service.interfaces.Feedbacks;
import com.example.onetoone.core.service.interfaces.KafkaGateway;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import com.example.onetoone.core.service.interfaces.Technologies;
import com.example.onetoone.core.service.interfaces.UserAnswers;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.user.entities.User;
import com.example.onetoone.presentation.request.UserAnswerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateFeedbackInteractor implements Interactor<CreateFeedbackCommand, FeedbackResult> {
    private final Feedbacks feedbacks;
    private final FeedbackMapper mapper;
    private final Users users;
    private final OneToOnes oneToOnes;
    private final UserAnswers userAnswers;
    private final Technologies technologies;
    private final UserAnswerMapper userAnswerMapper;
    private final KafkaGateway kafkaGateway;

    @Override
    public FeedbackResult execute(CreateFeedbackCommand command) {
        log.info("Executing command {}", command);

        feedbacks.getByOneToOneIdAndAuthorId(command.getOneToOneId(), command.getAuthorId()).ifPresent(
                feedback-> {
                    throw new ServiceException(ServiceException.Exception.FEEDBACK_ALREADY_HAS_BEEN_WRITTEN, feedback.getId());
                });

        var author = users.get(command.getAuthorId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND, command.getAuthorId()));
        var recipient = users.get(command.getRecipientId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND, command.getRecipientId()));
        var oneToOne = oneToOnes.get(command.getOneToOneId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND, command.getOneToOneId()));


        var entity = mapper.toEntity(command, author, recipient, oneToOne);
        entity.isValid();
        setFeedbackStatus(oneToOne, author);
        var feedback = getFeedback(entity);
        var answers = toUserAnswer(command.getQuestions());
        var entityList = saveUserAnswer(answers, feedback);
        kafkaGateway.sendUserAnswerEvent(new UserAnswerEvent(entityList, recipient));
        oneToOnes.put(oneToOne);
        return mapper.toResult(feedback, entityList);
    }

    private void setFeedbackStatus(OneToOne oneToOne, User author) {
        if (author.getId()==oneToOne.getInitiator().getId()){
            oneToOne.setInitiatorFeedback(FeedbackStatus.WRITE);
        } else {
            oneToOne.setOpponentFeedback(FeedbackStatus.WRITE);
        }
    }

    private List<UserAnswer> toUserAnswer(List<UserAnswerRequest> questions) {
        List<UserAnswer> resultLis = new ArrayList<>();
        for (UserAnswerRequest usr : questions) {
            var technology = technologies.get(usr.getQuestion().getTechnologyId()).get();
            var ua = userAnswerMapper.toUserAnswer(usr);
            ua.getQuestion().setTechnology(technology);
            resultLis.add(ua);
        }
        return resultLis;
    }

    private List<UserAnswer> saveUserAnswer(List<UserAnswer> questions, Feedback feedback) {
        return questions.stream()
                .map(an -> userAnswerMapper.toEntity(an, feedback.getId()))
                .peek(userAnswers::put).toList();
    }

    private Feedback getFeedback(Feedback feedback) {
        return feedbacks.put(feedback);
    }

}
