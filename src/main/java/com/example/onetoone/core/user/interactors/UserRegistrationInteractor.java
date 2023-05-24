package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.UserRoles;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.user.UserMapper;
import com.example.onetoone.core.user.commands.UserRegistrationCommand;
import com.example.onetoone.core.user.results.UserRegistrationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegistrationInteractor implements Interactor<UserRegistrationCommand, UserRegistrationResult> {
    private final Users users;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoles userRoles;

    @Override
    public UserRegistrationResult execute(UserRegistrationCommand command) {
        log.info("Executing command {}", command);
        var user = users.getByTelegramUserId(command.getTelegramUserId());
        if (user.isPresent()) {
            throw new ServiceException(ServiceException.Exception.USER_BY_EMAIL_ALREADY_REGISTERED, command.getTelegramUserName());
        } else {
            var entity = mapper.toEntity(command);
            entity.setPassword(passwordEncoder.encode(command.getPassword()));
            entity.setRole(userRoles.getSimpleUserRole());
            return mapper.toUserRegistrationResult(users.put(entity));
        }
    }
}
