package com.example.onetoone.core.feedback.interactors;

import com.example.onetoone.core.feedback.commands.GetUserTechnologyStatisticsCommand;
import com.example.onetoone.core.feedback.results.UserTechnologyStatisticsResult;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.UsersTechnologyStatistics;
import com.example.onetoone.inrastructure.data.mappers.UsersTechnologyStatisticsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUsersTechnologyStatisticsInteractor implements Interactor<GetUserTechnologyStatisticsCommand,
        ResultModelList<UserTechnologyStatisticsResult>> {

    private final UsersTechnologyStatistics usersTechnologyStatistics;
    private final UsersTechnologyStatisticsMapper mapper;

    @Override
    public ResultModelList<UserTechnologyStatisticsResult> execute(GetUserTechnologyStatisticsCommand command) {
        log.info("Executing command {}", command);

        var list= usersTechnologyStatistics.getById(command.getId());
        var entityList = new EntityList<>(list.size(), list);

        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toResult).collect(Collectors.toList()));
    }
}
