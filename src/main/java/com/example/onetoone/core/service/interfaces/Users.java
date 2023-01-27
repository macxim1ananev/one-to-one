package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.user.entities.User;

import java.util.Optional;

public interface Users {
    User put(User entity);

    Optional<User> get(long id);

    Optional<User> loadUserByLogin(String email);
}
