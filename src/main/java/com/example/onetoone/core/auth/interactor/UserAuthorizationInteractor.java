package com.example.onetoone.core.auth.interactor;

import com.example.onetoone.core.auth.UserAuthorizationMapper;
import com.example.onetoone.core.auth.command.UserAuthorizationCommand;
import com.example.onetoone.core.auth.result.UserAuthorizationResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.interfaces.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthorizationInteractor implements Interactor<UserAuthorizationCommand, UserAuthorizationResult> {

    private final AuthenticationManager authenticationManager;
    private final Users users;
    private final UserAuthorizationMapper mapper;

    @Override
    public UserAuthorizationResult execute(UserAuthorizationCommand command) {
        log.info("Executing command {}", command);

       return null;

    }
}
