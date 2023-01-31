package com.example.onetoone.core.auth.interactor;

import com.example.onetoone.config.security.JwtTokenUtil;
import com.example.onetoone.core.auth.UserAuthorizationMapper;
import com.example.onetoone.core.auth.command.UserAuthorizationCommand;
import com.example.onetoone.core.auth.result.UserAuthorizationResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthorizationInteractor implements Interactor<UserAuthorizationCommand, UserAuthorizationResult> {

    private final AuthenticationManager authenticationManager;
    private final Users users;
    private final UserAuthorizationMapper mapper;
    private final JwtTokenUtil jwtUtils;

    @Override
    public UserAuthorizationResult execute(UserAuthorizationCommand command) {
        log.info("Executing command {}", command);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(command.getEmail(), command.getPassword()));
        var user = users.loadUserByEmail(command.getEmail())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_BY_EMAIL_NOT_FOUND, command.getEmail()));

        String jwt = jwtUtils.generateAccessToken(user);
        String refreshJwt = jwtUtils.generateRefreshToken(user);

       return mapper.toResult(jwt, refreshJwt);

    }
}
