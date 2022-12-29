package com.example.onetoone.core.rating.interactor;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.rating.entity.Rating;
import com.example.onetoone.core.rating.entity.TechnologyAnswer;
import com.example.onetoone.core.user.entities.User;

import java.util.List;

public class RatingService {

    public void updateRating(List<UserAnswer> userAnswers, User user){
        Rating rating = new Rating();
        rating.setUserId(user.getId());
        rating.setTotalQuestionCount(updateQuestionCount(userAnswers, rating.getTotalQuestionCount()));
        rating.setTotalAnswerCount(updateAnswerCount(userAnswers, rating.getTotalAnswerCount()));
        rating.setAverageEstimation(updateAverageEstimation(userAnswers, rating.getAverageEstimation()));
        updateTechnologyAnswer(userAnswers, rating.getTechnologyAnswer());
    }

    private void updateTechnologyAnswer(List<UserAnswer> userAnswers, List<TechnologyAnswer> oldList) {

    }

    private Double updateAverageEstimation(List<UserAnswer> userAnswers, Double oldValue) {
        Double result;
        for (UserAnswer ua : userAnswers) {
            ua.getResponseLevel().
        }
        return null;
    }

    private Integer updateAnswerCount(List<UserAnswer> userAnswers, Integer oldValue) {
        return oldValue+userAnswers.size();
    }

    private Integer updateQuestionCount(List<UserAnswer> userAnswers, Integer oldValue) {
        return oldValue+userAnswers.size();
    }
}
