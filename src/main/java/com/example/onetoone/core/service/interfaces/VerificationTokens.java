package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.user.entities.VerificationToken;

import java.util.Optional;

public interface VerificationTokens {
    VerificationToken save(VerificationToken verificationToken);

    Optional<VerificationToken> getUserVerificationToken(String token);
}
