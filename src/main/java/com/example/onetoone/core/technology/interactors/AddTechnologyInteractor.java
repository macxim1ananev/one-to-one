package com.example.onetoone.core.technology.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.interfaces.Technologies;
import com.example.onetoone.core.technology.TechnologyMapper;
import com.example.onetoone.core.technology.commands.AddTechnologyCommand;
import com.example.onetoone.core.technology.results.TechnologyResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class AddTechnologyInteractor implements Interactor<AddTechnologyCommand, TechnologyResult> {

    private final Technologies technologies;
    private final TechnologyMapper mapper;

    @Override
    public TechnologyResult execute(AddTechnologyCommand command) {
        log.info("Executing command {}", command);

        var entity = mapper.toEntity(command);
        return mapper.toResult(technologies.put(entity));
    }
}
