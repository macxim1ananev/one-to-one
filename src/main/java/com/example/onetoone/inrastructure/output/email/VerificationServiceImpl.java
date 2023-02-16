package com.example.onetoone.inrastructure.output.email;

import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.service.interfaces.VerificationTokens;
import com.example.onetoone.core.user.entities.UserStatus;
import com.example.onetoone.core.user.entities.VerificationToken;
import com.example.onetoone.inrastructure.output.data.mappers.VerificationTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final VerificationTokens verificationTokens;
    private final Users users;

    private final VerificationTokenMapper verificationTokenMapper;

    @Override
    @Transactional
    public void createVerificationToken(Long userId, String token) {
        VerificationToken verificationToken = verificationTokenMapper.toVerificationToken(userId, token);
        verificationToken.setExpirationDate(1440L);
        verificationTokens.save(verificationToken);
    }

    @Override
    @Transactional
    public String confirmationUserRegistration(String token) {
        VerificationToken verificationToken = getVerificationToken(token);
        if (LocalDateTime.now().isAfter(verificationToken.getExpiryDate())){
            throw new ServiceException(ServiceException.Exception.VERIFICATION_TOKEN_HAS_EXPIRED);
        }
        var user = users.get(verificationToken.getUserId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND, verificationToken.getUserId()));

        user.setStatus(UserStatus.ACTIVATED);
        users.put(user);
        return Message.CONFIRM_REGISTRATION_SUCCESSFULLY;
    }

    private VerificationToken getVerificationToken(String token) {
        return verificationTokens.getUserVerificationToken(token).orElseThrow(
                () -> new ServiceException(ServiceException.Exception.INVALID_TOKEN_FOR_CONFIRM_REGISTRATION_USER));
    }
}
