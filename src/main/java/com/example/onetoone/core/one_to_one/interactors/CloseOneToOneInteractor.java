package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.CloseOneToOneCommand;
import com.example.onetoone.core.one_to_one.entities.OneToOneStatus;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CloseOneToOneInteractor implements Interactor<CloseOneToOneCommand, OneToOneResult> {

    private final OneToOnes oneToOnes;
    private final OneToOneMapper mapper;

    @Override
    public OneToOneResult execute(CloseOneToOneCommand command) {
        var oneToOne = oneToOnes.get(command.getId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND));
        if (oneToOne.getInitiator().getId().equals(command.getAuthorId())){
            oneToOne.setStatus(OneToOneStatus.CLOSED);
            return mapper.toResult(oneToOnes.put(oneToOne));
        } else {
            throw new ServiceException(ServiceException.Exception.ONE_TO_ONE_CLOSE_IMPOSSIBLE);
        }
    }
}
