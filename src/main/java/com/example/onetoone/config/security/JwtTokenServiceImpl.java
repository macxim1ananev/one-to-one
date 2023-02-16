package com.example.onetoone.config.security;

import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.presentation.request.RefreshJwtTokenRequest;
import com.example.onetoone.presentation.request.UserAuthorizationRequest;
import com.example.onetoone.presentation.view.JwtTokenView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final AuthenticationManager authenticationManager;
    private final Users users;
    private final JwtTokenUtil jwtUtils;


    public JwtTokenResult authenticate(UserAuthorizationRequest request) {
        log.info("Authenticate and get jwt token");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = users.loadUserByEmail(request.getEmail())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_BY_EMAIL_NOT_FOUND, request.getEmail()));

        String accessToken = jwtUtils.generateAccessToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);

        return JwtTokenResult
                .builder()
                .jwtToken(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }

    public JwtTokenResult refreshAccessToken(String refreshJwt) {
        log.info("Getting a new access token");

        String email = jwtUtils.getEmailFromRefreshToken(refreshJwt);

        if (jwtUtils.isValidRefreshToken(refreshJwt)) {
            var user = users.loadUserByEmail(email)
                    .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_BY_EMAIL_NOT_FOUND, email));

            String newAccessToken = jwtUtils.generateAccessToken(user);
            String newRefreshToken = jwtUtils.generateRefreshToken(user);
            return JwtTokenResult
                    .builder()
                    .jwtToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .email(user.getEmail())
                    .id(user.getId())
                    .build();
        }
        throw new ServiceException(ServiceException.Exception.TOKEN_INVALID_MESS);
    }
}
