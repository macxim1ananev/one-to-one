package com.example.onetoone.core.technology.interactors;

import com.example.onetoone.core.service.common.GetListInteractor;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.Technologies;
import com.example.onetoone.core.technology.TechnologyMapper;
import com.example.onetoone.core.technology.commands.GetFilteredAndSortedTechnologyListCommand;
import com.example.onetoone.core.technology.entities.Technology;
import com.example.onetoone.core.technology.results.TechnologyResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetTechnologyListInteractor extends GetListInteractor
        implements Interactor<GetFilteredAndSortedTechnologyListCommand, ResultModelList<TechnologyResult>> {

    private final Technologies technologies;
    private final TechnologyMapper mapper;

    @Override
    public ResultModelList<TechnologyResult> execute(GetFilteredAndSortedTechnologyListCommand command) {
        var filter = getListFilter(command, Technology.class);

        var entityList = technologies.getAll(filter);

        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toResult).collect(Collectors.toList()));
    }
}
