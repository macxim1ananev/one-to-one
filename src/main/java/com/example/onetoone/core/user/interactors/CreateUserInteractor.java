package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.user.UserMapper;
import com.example.onetoone.core.user.commands.CreateUserCommand;
import com.example.onetoone.core.user.results.UserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserInteractor implements Interactor<CreateUserCommand, UserResult> {
    private final Users users;
    private final UserMapper mapper;
    @Override
    public UserResult execute(CreateUserCommand command) {
        var entity = mapper.toEntity(command);
        return mapper.toResult(users.put(entity));
    }
}
