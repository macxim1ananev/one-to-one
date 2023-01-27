package com.example.onetoone.core.technology.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Technologies;
import com.example.onetoone.core.technology.TechnologyMapper;
import com.example.onetoone.core.technology.commands.GetTechnologyCommand;
import com.example.onetoone.core.technology.results.TechnologyResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetTechnologyInteractor implements Interactor<GetTechnologyCommand, TechnologyResult> {

    private final Technologies technologies;
    private final TechnologyMapper mapper;

    @Override
    public TechnologyResult execute(GetTechnologyCommand command) {
        log.info("Executing command {}", command);

        var entity = technologies.get(command.getId())
                .orElseThrow(()-> new ServiceException(ServiceException.Exception.TECHNOLOGY_NOT_FOUND, command.getId()));
        return mapper.toResult(entity);
    }
}
