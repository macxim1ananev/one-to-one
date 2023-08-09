package com.example.onetoone.core.statistics.interactor;

import com.example.onetoone.core.statistics.UserTechnologyStatisticMapper;
import com.example.onetoone.core.statistics.command.GetFilteredAndSortedUserTechnologyStatisticsListCommand;
import com.example.onetoone.core.statistics.result.UserTechnologyStatisticsResult;
import com.example.onetoone.core.service.common.GetListInteractor;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.UsersStatistics;
import com.example.onetoone.core.service.interfaces.UsersTechnologyStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetFilteredAndSortedUserTechnologyStatisticsInteractor extends GetListInteractor
        implements Interactor<GetFilteredAndSortedUserTechnologyStatisticsListCommand, ResultModelList<UserTechnologyStatisticsResult>> {

    private final UsersTechnologyStatistics usersTechnologyStatistics;
    private final UserTechnologyStatisticMapper mapper;
    @Override
    public ResultModelList<UserTechnologyStatisticsResult> execute(GetFilteredAndSortedUserTechnologyStatisticsListCommand command) {
        log.info("Executing command {}", command);

        var filter = getListFilter(command, UsersStatistics.class);
        var entityList = usersTechnologyStatistics.getAll(filter);
        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toResult).collect(Collectors.toList()));
    }
}
