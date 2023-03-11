package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.feedback.entities.Feedback;
import com.example.onetoone.core.service.interfaces.Feedbacks;
import com.example.onetoone.inrastructure.output.data.mappers.FeedbackModelMapper;
import com.example.onetoone.inrastructure.output.data.repositories.FeedbackRepository;
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

    @Override
    public Optional<Feedback> getByOneToOneIdAndRecipientId(Long oneToOneId, Long recipientId) {
        return repository.getByOneToOneIdAndRecipientId(oneToOneId, recipientId).map(mapper::toEntity);
    }

    @Override
    public Optional<Feedback> getByOneToOneIdAndAuthorId(long oneToOneId, long authorId) {
        return repository.getByOneToOneIdAndAuthorId(oneToOneId, authorId).map(mapper::toEntity);
    }
}
