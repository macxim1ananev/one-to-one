package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.user.UserMapper;
import com.example.onetoone.core.user.commands.GetUserCommand;
import com.example.onetoone.core.user.results.UserResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserInteractor implements Interactor<GetUserCommand, UserResult> {
    private final Users users;
    private final UserMapper mapper;

    @Override
    public UserResult execute(GetUserCommand command) {
        log.info("Executing command {}", command);

        var entity = users.get(command.getId()).orElseThrow(
                () -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));

        return mapper.toResult(entity);
    }
}
