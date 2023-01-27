package com.example.onetoone.core.auth.result;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserAuthorizationResult {
    String jwtToken;
    String refreshToken;
}
