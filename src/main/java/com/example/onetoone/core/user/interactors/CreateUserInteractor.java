package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.user.UserMapper;
import com.example.onetoone.core.user.commands.CreateUserCommand;
import com.example.onetoone.core.user.results.UserResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserInteractor implements Interactor<CreateUserCommand, UserResult> {
    private final Users users;
    private final UserMapper mapper;


    @Override
    public UserResult execute(CreateUserCommand command) {
        log.info("Executing command {}", command);
        var user = users.loadUserByEmail(command.getEmail());
        if (user.isPresent()) {
            throw new ServiceException(ServiceException.Exception.USER_BY_EMAIL_ALREADY_REGISTERED, command.getEmail());
        } else {
            var entity = mapper.toEntity(command);
            entity.setPassword(command.getPassword());
            return mapper.toResult(users.put(entity));
        }
    }
}
