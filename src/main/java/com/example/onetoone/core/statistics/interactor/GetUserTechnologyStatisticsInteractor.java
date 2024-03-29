package com.example.onetoone.core.statistics.interactor;

import com.example.onetoone.core.statistics.UserTechnologyStatisticMapper;
import com.example.onetoone.core.statistics.command.GetUserTechnologyStatisticsCommand;
import com.example.onetoone.core.statistics.result.UserTechnologyStatisticsResult;
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
public class GetUserTechnologyStatisticsInteractor
        implements Interactor<GetUserTechnologyStatisticsCommand, ResultModelList<UserTechnologyStatisticsResult>> {

    private final UsersTechnologyStatistics usersTechnologyStatistics;
    private final UsersStatistics usersStatistics;
    private final UserTechnologyStatisticMapper mapper;

    @Override
    public  ResultModelList<UserTechnologyStatisticsResult> execute(GetUserTechnologyStatisticsCommand command) {
        log.info("Executing command {}", command);

        var userStatistics = usersStatistics.get(command.getId())
                .orElseThrow(()-> new ServiceException(ServiceException.Exception.USER_STATISTICS_NOT_FOUND, command.getId()));
        var list= usersTechnologyStatistics.getById(userStatistics.getId());
        var entityList = new EntityList<>(list.size(), list);

        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toTechnologyStatisticsResult).collect(Collectors.toList()));
    }
}
