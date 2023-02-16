package com.example.onetoone.config.security;

import com.example.onetoone.presentation.request.RefreshJwtTokenRequest;
import com.example.onetoone.presentation.request.UserAuthorizationRequest;
import com.example.onetoone.presentation.view.JwtTokenView;

public interface JwtTokenService {
    JwtTokenResult authenticate(UserAuthorizationRequest request);

    JwtTokenResult refreshAccessToken(String refreshJwt);
}
