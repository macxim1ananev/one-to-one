package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.service.interfaces.VerificationTokens;
import com.example.onetoone.core.user.entities.VerificationToken;
import com.example.onetoone.inrastructure.output.data.mappers.VerificationTokenMapper;
import com.example.onetoone.inrastructure.output.data.repositories.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VerificationTokensAdapter implements VerificationTokens {
    private final VerificationTokenRepository repository;
    private final VerificationTokenMapper mapper;

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        var model = mapper.toModel(verificationToken);
        return mapper.toEntity(repository.save(model));
    }

    @Override
    public Optional<VerificationToken> getUserVerificationToken(String token) {
        return repository.getUserVerificationToken(token).map(mapper::toEntity);
    }
}
