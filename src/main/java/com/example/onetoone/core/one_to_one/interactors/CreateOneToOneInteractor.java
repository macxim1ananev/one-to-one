package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.CreateOneToOneCommand;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import com.example.onetoone.core.service.interfaces.Technologies;
import com.example.onetoone.core.service.interfaces.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOneToOneInteractor implements Interactor<CreateOneToOneCommand, OneToOneResult> {
    private final OneToOnes oneToOnes;
    private final Users users;
    private final Technologies technologies;
    private final OneToOneMapper mapper;
    @Override
    public OneToOneResult execute(CreateOneToOneCommand command) {
        log.info("Executing command {}", command);

        var technology = technologies.get(command.getTechnologyId())
                .orElseThrow(()-> new ServiceException(ServiceException.Exception.TECHNOLOGY_NOT_FOUND));
        var initiator = users.get(command.getInitiatorId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));
        var entity = mapper.toEntity(command, initiator, technology);

        return mapper.toResult(oneToOnes.put(entity));
    }
}
