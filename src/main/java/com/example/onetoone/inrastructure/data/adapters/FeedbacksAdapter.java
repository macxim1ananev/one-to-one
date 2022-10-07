package com.example.onetoone.inrastructure.data.adapters;

import com.example.onetoone.core.feedback.entities.Feedback;
import com.example.onetoone.core.service.interfaces.Feedbacks;
import com.example.onetoone.inrastructure.data.mappers.FeedbackModelMapper;
import com.example.onetoone.inrastructure.data.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FeedbacksAdapter implements Feedbacks {
    private final FeedbackModelMapper mapper;
    private final FeedbackRepository repository;
    @Override
    public Feedback put(Feedback feedback) {
        var model = mapper.toModel(feedback);
        return mapper.toEntity(repository.save(model));
    }

    @Override
    public Optional<Feedback> get(Long id) {

        return repository.findById(id).map(mapper::toEntity);
    }
}
