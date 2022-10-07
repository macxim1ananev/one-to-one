package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.feedback.entities.Feedback;

import java.util.Optional;

public interface Feedbacks {

    Feedback put(Feedback feedback);

    Optional<Feedback> get(Long id);
}
