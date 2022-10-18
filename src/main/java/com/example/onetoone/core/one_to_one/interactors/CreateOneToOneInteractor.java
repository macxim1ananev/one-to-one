package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.CreateOneToOneCommand;
import com.example.onetoone.core.one_to_one.entities.OneToOneStatus;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import com.example.onetoone.core.service.interfaces.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOneToOneInteractor implements Interactor<CreateOneToOneCommand, OneToOneResult> {
    private final OneToOnes oneToOnes;
    private final Users users;
    private final OneToOneMapper mapper;
    @Override
    public OneToOneResult execute(CreateOneToOneCommand command) {
        var entity = mapper.toEntity(command);
        var initiator = users.get(command.getInitiatorId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));
        entity.setInitiator(initiator);
        entity.setStatus(OneToOneStatus.OPEN);

        return mapper.toResult(oneToOnes.put(entity));
    }
}
