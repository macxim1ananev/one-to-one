package com.example.onetoone.core.auth.result;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshJwtTokenResult {
    String jwtToken;
    String refreshToken;
}
