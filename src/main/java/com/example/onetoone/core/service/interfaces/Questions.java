package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.question.entities.Question;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;

import java.util.List;
import java.util.Optional;

public interface Questions {
    Question put(Question entity);

    List<Question> getAllByUserId(long authorId);

    EntityList<Question> getAll(ListFilter filter);

    Optional<Question> getById(long id);
}
