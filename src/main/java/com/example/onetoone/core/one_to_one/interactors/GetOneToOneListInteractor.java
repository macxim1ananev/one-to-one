package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.GetFilteredAndSortedOneToOneListCommand;
import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.service.common.GetListInteractor;
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
public class GetOneToOneListInteractor extends GetListInteractor
        implements Interactor<GetFilteredAndSortedOneToOneListCommand, ResultModelList<OneToOneResult>> {

    private final OneToOnes oneToOnes;
    private final OneToOneMapper mapper;

    @Override
    public ResultModelList<OneToOneResult> execute(GetFilteredAndSortedOneToOneListCommand command) {
        log.info("Executing command {}", command);

        var filter = getListFilter(command, OneToOne.class);
        var entityList = oneToOnes.getAll(filter);
        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toResult).collect(Collectors.toList()));    }
}
