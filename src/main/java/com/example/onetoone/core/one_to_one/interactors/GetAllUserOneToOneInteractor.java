package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.GetAllUserOneToOneCommand;
import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAllUserOneToOneInteractor implements Interactor<GetAllUserOneToOneCommand, ResultModelList<OneToOneResult>> {
    private final OneToOnes oneToOnes;
    private final OneToOneMapper mapper;

    @Override
    public ResultModelList<OneToOneResult> execute(GetAllUserOneToOneCommand command) {
        log.info("Executing command {}", command);

        EntityList<OneToOne> entityList = oneToOnes.getAllUserOneToOne(command.getId());
        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toResult).collect(Collectors.toList()));
    }
}
