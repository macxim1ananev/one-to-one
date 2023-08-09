package com.example.onetoone.core.statistics.interactor;

import com.example.onetoone.core.statistics.UserTechnologyStatisticMapper;
import com.example.onetoone.core.statistics.command.GetFullUserStatisticsCommand;
import com.example.onetoone.core.statistics.result.FullUserStatisticsResult;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.UsersStatistics;
import com.example.onetoone.core.service.interfaces.UsersTechnologyStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetFullUsersStatisticsInteractor implements Interactor<GetFullUserStatisticsCommand,
        ResultModelList<FullUserStatisticsResult>> {

    private final UsersTechnologyStatistics usersTechnologyStatistics;
    private final UsersStatistics usersStatistics;
    private final UserTechnologyStatisticMapper mapper;

    @Override
    public ResultModelList<FullUserStatisticsResult> execute(GetFullUserStatisticsCommand command) {
        log.info("Executing command {}", command);

        var userStatistics = usersStatistics.get(command.getId())
                .orElseThrow(()-> new ServiceException(ServiceException.Exception.USER_STATISTICS_NOT_FOUND, command.getId()));

        var list= usersTechnologyStatistics.getById(userStatistics.getId());
        var entityList = new EntityList<>(list.size(), list);

        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toFullUserStatisticsResult).collect(Collectors.toList()));
    }
}
