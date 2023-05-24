package com.example.onetoone.core.statistics.interactors;

import com.example.onetoone.core.statistics.commands.GetFilteredAndSortedUserStatisticsListCommand;
import com.example.onetoone.core.statistics.results.UserStatisticsResult;
import com.example.onetoone.core.service.common.GetListInteractor;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.UsersStatistics;
import com.example.onetoone.core.statistics.UsersStatisticsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAllFilteredAndSortedUserStatisticsInteractor extends GetListInteractor
        implements Interactor<GetFilteredAndSortedUserStatisticsListCommand, ResultModelList<UserStatisticsResult>> {

    private final UsersStatistics usersStatistics;
    private final UsersStatisticsMapper mapper;

    @Override
    public ResultModelList<UserStatisticsResult> execute(GetFilteredAndSortedUserStatisticsListCommand command) {
        log.info("Executing command {}", command);

        var filter = getListFilter(command, UsersStatistics.class);
        var entityList = usersStatistics.getAll(filter);
        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toResult).collect(Collectors.toList()));
    }
}
