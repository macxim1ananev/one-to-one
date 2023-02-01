package com.example.onetoone.inrastructure.data.adapters;

import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.user.entities.User;
import com.example.onetoone.inrastructure.data.mappers.UserModelMapper;
import com.example.onetoone.inrastructure.data.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements Users {
    private final UserRepository repository;
    private final UserModelMapper mapper;
    @Override
    public User put(User entity) {
        var userModel = mapper.toModel(entity);
        return mapper.toEntity(repository.save(userModel));
    }

    @Override
    public Optional<User> get(long id) {
        return repository.findById(id).map(mapper::toEntity);
    }

    @Override
    public Optional<User> loadUserByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toEntity);
    }
}
