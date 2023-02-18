package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.user.UserMapper;
import com.example.onetoone.core.user.commands.PreRegistrationUserCommand;
import com.example.onetoone.core.user.results.PreRegistrationUserResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class PreRegistrationUserInteractor implements Interactor<PreRegistrationUserCommand, PreRegistrationUserResult> {
    private final Users users;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PreRegistrationUserResult execute(PreRegistrationUserCommand command) {
        log.info("Executing command {}", command);

        var user = users.loadUserByEmail(command.getEmail());
        if (user.isPresent()) {
            return mapper.toPreRegistrationUserResult(user.get());
        } else {
            String temporaryPassword = getTemporaryPassword();
            var entity = mapper.toEntity(command);
            entity.setName("Неизвестный");
            entity.setSurName("Программист");
            entity.setPassword(passwordEncoder.encode(temporaryPassword));
            return mapper.toPreRegistrationUserResult(users.put(entity));
        }
    }

    private String getTemporaryPassword() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}