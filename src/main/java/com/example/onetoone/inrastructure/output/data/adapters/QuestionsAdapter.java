package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.question.entities.Question;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.service.interfaces.Questions;
import com.example.onetoone.inrastructure.output.data.FilteringAndSortingAdapter;
import com.example.onetoone.inrastructure.output.data.mappers.QuestionsModelMapper;
import com.example.onetoone.inrastructure.output.data.models.QuestionModel;
import com.example.onetoone.inrastructure.output.data.repositories.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionsAdapter extends FilteringAndSortingAdapter<QuestionModel> implements Questions {

    private final QuestionsModelMapper mapper;
    private final QuestionsRepository repository;

    @Override
    public Question put(Question entity) {
        return mapper.toEntity(repository.save(mapper.toModel(entity)));
    }

    @Override
    public List<Question> getAllByUserId(long authorId) {
        List<QuestionModel> questions = repository.findAllByUserId(authorId);
        return questions.stream().map(mapper::toEntity).collect(Collectors.toList());
    }

    @Override
    public EntityList<Question> getAll(ListFilter filter) {
        var page = repository.findAll(getSpecification(filter), getPageable(filter));
        return new EntityList<>(page.getTotalElements(), page.map(mapper::toEntity).getContent());
    }

    @Override
    public Optional<Question> getById(long id) {
        return repository.findById(id).map(mapper::toEntity);
    }
}
