package com.example.onetoone.config.security;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JwtTokenResult {
    String jwtToken;
    String refreshToken;
    String email;
    Long id;
}
