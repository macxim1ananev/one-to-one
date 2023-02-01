package com.example.onetoone.core.auth.interactor;

import com.example.onetoone.config.security.JwtTokenUtil;
import com.example.onetoone.core.auth.UserAuthorizationMapper;
import com.example.onetoone.core.auth.command.RefreshJwtTokenCommand;
import com.example.onetoone.core.auth.result.RefreshJwtTokenResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshJwtTokenInteractor implements Interactor<RefreshJwtTokenCommand, RefreshJwtTokenResult> {
    private final Users users;
    private final UserAuthorizationMapper mapper;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public RefreshJwtTokenResult execute(RefreshJwtTokenCommand command) {
        log.info("Executing command {}", command);

        String email = jwtTokenUtil.getEmailFromRefreshToken(command.getRefreshToken());

        if (jwtTokenUtil.isValidRefreshToken(command.getRefreshToken())) {
            var user = users.loadUserByEmail(email)
                    .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_BY_EMAIL_NOT_FOUND, email));

            String newAccessToken = jwtTokenUtil.generateAccessToken(user);
            String newRefreshToken = jwtTokenUtil.generateRefreshToken(user);
            return mapper.toRefreshTokenResult(newAccessToken, newRefreshToken);
        }
        throw new ServiceException(ServiceException.Exception.TOKEN_INVALID_MESS);
    }
}
