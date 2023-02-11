package com.example.onetoone.presentation;

import com.example.onetoone.config.security.JwtTokenService;
import com.example.onetoone.presentation.request.RefreshJwtTokenRequest;
import com.example.onetoone.presentation.request.UserAuthorizationRequest;
import com.example.onetoone.presentation.view.JwtTokenView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final JwtTokenService jwtTokenService;


    @PostMapping("/jwt")
    public JwtTokenView oneTimeAuthorization(@RequestBody UserAuthorizationRequest request) {
        log.info("Request to receive a jwt token");

        return jwtTokenService.authenticate(request);
    }

    @PostMapping("jwt/refresh")
    public JwtTokenView refreshJwtToken(@RequestBody RefreshJwtTokenRequest request) {
        log.info("Request to update a jwt token");

        return jwtTokenService.refreshAccessToken(request);
    }
}
