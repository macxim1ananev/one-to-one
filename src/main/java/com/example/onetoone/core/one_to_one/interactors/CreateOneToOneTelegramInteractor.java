package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.CreateOneToOneTelegramCommand;
import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.one_to_one.entities.OneToOneStatus;
import com.example.onetoone.core.one_to_one.results.OneToOneTelegramResult;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import com.example.onetoone.core.service.interfaces.Technologies;
import com.example.onetoone.core.service.interfaces.Users;
import com.example.onetoone.core.user.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOneToOneTelegramInteractor implements Interactor<CreateOneToOneTelegramCommand, OneToOneTelegramResult> {
    private final OneToOnes oneToOnes;
    private final Users users;
    private final Technologies technologies;
    private final OneToOneMapper mapper;
    @Override
    public OneToOneTelegramResult execute(CreateOneToOneTelegramCommand command) {
        log.info("Executing command {}", command);

        var technology = technologies.get(command.getTechnologyId())
                .orElseThrow(()-> new ServiceException(ServiceException.Exception.TECHNOLOGY_NOT_FOUND, command.getTechnologyId()));
        var initiator = users.getByTelegramUserId(command.getTelegramUserId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND, command.getTelegramUserId()));
        var entity = mapper.toEntity(command, initiator, technology);
        var listEntity = oneToOnes.getAllOpen(OneToOneStatus.OPEN.getId());

        if (listEntity.getTotalItems()>0){
            return mapper.toTelegramResult(acceptAlreadyOpen(listEntity, initiator), initiator.getTelegramUserName());
        }
        return mapper.toTelegramResult(oneToOnes.put(entity), StringUtils.EMPTY);
    }

    private OneToOne acceptAlreadyOpen(EntityList<OneToOne> listEntity, User opponent) {
        var oneToOne = listEntity.getItems().get(0);
        oneToOne.setOpponent(opponent);
        oneToOne.setStatus(OneToOneStatus.CLOSED);
        return oneToOnes.put(oneToOne);
    }
}
