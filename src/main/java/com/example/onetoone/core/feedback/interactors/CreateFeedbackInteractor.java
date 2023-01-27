package com.example.onetoone.core.feedback.interactors;

import com.example.onetoone.core.feedback.FeedbackMapper;
import com.example.onetoone.core.feedback.UserAnswerMapper;
import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.entities.Feedback;
import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.feedback.entities.statistics.UserStatistics;
import com.example.onetoone.core.feedback.entities.statistics.UserTechnologyStatistics;
import com.example.onetoone.core.feedback.results.FeedbackResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.*;
import com.example.onetoone.core.user.entities.User;
import com.example.onetoone.presentation.request.UserAnswerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateFeedbackInteractor implements Interactor<CreateFeedbackCommand, FeedbackResult> {
    private final Feedbacks feedbacks;
    private final FeedbackMapper mapper;
    private final Users users;
    private final OneToOnes oneToOnes;
    private final UserAnswers userAnswers;
    private final UsersStatistics usersStatistics;
    private final UsersTechnologyStatistics usersTechnologyStatistics;
    private final Technologies technologies;
    private final UserAnswerMapper userAnswerMapper;

    @Override
    public FeedbackResult execute(CreateFeedbackCommand command) {
        log.info("Executing command {}", command);

        var author = users.get(command.getAuthorId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND, command.getAuthorId()));
        var recipient = users.get(command.getRecipientId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND, command.getRecipientId()));
        var oneToOne = oneToOnes.get(command.getOneToOneId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND, command.getOneToOneId()));

        var entity = mapper.toEntity(command, author, recipient, oneToOne);
        entity.isValid();
        var feedback = getFeedback(entity);
        var answers = toUserAnswer(command.getQuestions());
        var entityList = saveUserAnswer(answers, feedback);

        var userStatistics = saveStatistics(updateStatistics(recipient, entityList));
        saveUserTechnologyStatistics(updateUserTechnologyStatistics(entityList, userStatistics));
        return mapper.toResult(feedback, entityList);
    }

    private void saveUserTechnologyStatistics(List<UserTechnologyStatistics> statistics) {
        statistics.forEach(usersTechnologyStatistics::save);
    }

    private UserStatistics saveStatistics(UserStatistics statistics) {
        return usersStatistics.save(statistics);

    }

    private UserStatistics updateStatistics(User user, List<UserAnswer> userAnswers) {
        var userStatistics = usersStatistics.get(user.getId());

        if (userStatistics.isEmpty()) {
            var newUserStatistics = new UserStatistics();
            newUserStatistics.setUser(user);
            newUserStatistics.setTotalOneToOneCount(1);
            newUserStatistics.setTotalQuestionCount(userAnswers.size());
            newUserStatistics.setTotalPoint(getTotalPoint(userAnswers));
            return newUserStatistics;
        } else {
            var currentUserStatistics = userStatistics.get();
            currentUserStatistics.incrementOneToOneCount();
            currentUserStatistics.incrementTotalQuestionCount(userAnswers.size());
            currentUserStatistics.plusTotalPoint(getTotalPoint(userAnswers));
            return currentUserStatistics;
        }
    }

    private Integer getTotalPoint(List<UserAnswer> userAnswers) {
        Integer totalPoint = 0;
        for (UserAnswer ua : userAnswers) {
            totalPoint += ua.getResponseLevel();
        }
        return totalPoint;
    }

    private List<UserTechnologyStatistics> updateUserTechnologyStatistics(List<UserAnswer> userAnswers, UserStatistics userStatistics) {
        var statistics = usersTechnologyStatistics.getById(userStatistics.getId());

        if (statistics.isEmpty()) {
            Map<Long, UserTechnologyStatistics> map = new HashMap<>();
            for (UserAnswer ua : userAnswers) {
                var technologyId = ua.getQuestion().getTechnology().getId();
                var uts = map.get(technologyId);
                if (uts == null) {
                    uts = new UserTechnologyStatistics();
                    uts.setUserStatistics(userStatistics);
                    uts.setTechnology(technologies.get(technologyId).orElseThrow(
                            ()-> new ServiceException(ServiceException.Exception.TECHNOLOGY_NOT_FOUND, technologyId)));
                    uts.incrementQuestionCount();
                    uts.plusTotalPoint(ua.getResponseLevel());
                    map.put(technologyId, uts);
                } else {
                    uts.incrementQuestionCount();
                    uts.plusTotalPoint(ua.getResponseLevel());
                }
            }
            return map.values().stream().toList();
        } else {
            Map<Long, UserTechnologyStatistics> map = statistics
                    .stream()
                    .collect(Collectors.toMap(
                            ust -> ust.getTechnology().getId(), Function.identity()));

            for (UserAnswer ua : userAnswers) {
                var technologyId = ua.getQuestion().getTechnology().getId();
                var uts = map.get(technologyId);
                if (uts != null) {
                    uts.incrementQuestionCount();
                    uts.plusTotalPoint(ua.getResponseLevel());
                } else {
                    var newUts = new UserTechnologyStatistics();
                    newUts.setUserStatistics(userStatistics);
                    newUts.setQuestionCount(1);
                    newUts.setTechnology(technologies.get(technologyId).orElseThrow(
                            () -> new ServiceException(ServiceException.Exception.TECHNOLOGY_NOT_FOUND, technologyId)));
                    newUts.setTotalPoint(ua.getResponseLevel());
                    statistics.add(newUts);
                }
            }
            return map.values().stream().toList();
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
