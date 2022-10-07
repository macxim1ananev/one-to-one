package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.AcceptOneToOneCommand;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import com.example.onetoone.core.service.interfaces.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AcceptOneToOneInteractor implements Interactor<AcceptOneToOneCommand, OneToOneResult> {
    private final OneToOneMapper mapper;
    private final OneToOnes oneToOnes;
    private final Users users;

    @Override
    public OneToOneResult execute(AcceptOneToOneCommand command) {
        var entity = oneToOnes.get(command.getOneToOneId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND));
        var opponent = users.get(command.getOpponentId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));

        entity.validate(command.getOpponentId());
        entity.setOpponent(opponent);
        return mapper.toResult(oneToOnes.put(entity));
    }
}
