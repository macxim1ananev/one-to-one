package com.example.onetoone.presentation;

import com.example.onetoone.core.auth.command.RefreshJwtTokenCommand;
import com.example.onetoone.core.auth.command.UserAuthorizationCommand;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.presentation.mapper.AuthorizationViewMapper;
import com.example.onetoone.presentation.request.RefreshJwtTokenRequest;
import com.example.onetoone.presentation.request.UserAuthorizationRequest;
import com.example.onetoone.presentation.view.JwtTokenView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final CommandBus commandBus;
    private final AuthorizationViewMapper mapper;


    @PostMapping
    public JwtTokenView oneTimeAuthorization(@RequestBody UserAuthorizationRequest request) {
        log.info("Request to receive a jwt token");

        return mapper.toView(commandBus.execute(UserAuthorizationCommand
                .builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build()));
    }

    @PostMapping("/token")
    public JwtTokenView refreshJwtToken(@RequestBody RefreshJwtTokenRequest refreshTokenDto) {
        log.info("Request to update a jwt token");

        return commandBus.execute(RefreshJwtTokenCommand
                .builder()
                .refreshToken(refreshTokenDto.getRefreshToken())
                .build());
    }
}
