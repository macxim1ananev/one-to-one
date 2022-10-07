package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.question.entities.Question;

import java.util.List;

public interface Questions {
    Question put(Question entity);

    List<Question> getAllByUserId(long authorId);
}
