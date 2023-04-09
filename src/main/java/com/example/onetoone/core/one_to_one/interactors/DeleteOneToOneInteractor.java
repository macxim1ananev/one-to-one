package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.DeleteOneToOneCommand;
import com.example.onetoone.core.one_to_one.entities.OneToOneStatus;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import com.example.onetoone.core.service.interfaces.Technologies;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteOneToOneInteractor implements Interactor<DeleteOneToOneCommand, Void> {
    private final OneToOnes oneToOnes;
    private final Technologies technologies;
    private final OneToOneMapper mapper;

    @Override
    public Void execute(DeleteOneToOneCommand command) {
        log.info("Executing command {}", command);

        var entity = oneToOnes.get(command.getId()).orElseThrow(
                ()-> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND, command.getId()));
        if (entity.getStatus().equals(OneToOneStatus.OPEN)){
            oneToOnes.delete(entity);
            return null;
        } else {
            throw new ServiceException(ServiceException.Exception.ACCEPTED_ONE_TO_ONE_CANNOT_BE_DELETED);
        }
    }
}
