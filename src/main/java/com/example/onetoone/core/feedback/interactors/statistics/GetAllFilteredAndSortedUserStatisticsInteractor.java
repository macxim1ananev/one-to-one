package com.example.onetoone.core.feedback.interactors.statistics;

import com.example.onetoone.core.feedback.commands.statistics.GetFilteredAndSortedUserStatisticsListCommand;
import com.example.onetoone.core.feedback.results.statistics.UserStatisticsResult;
import com.example.onetoone.core.service.common.GetListInteractor;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.UsersStatistics;
import com.example.onetoone.inrastructure.output.data.mappers.UsersStatisticsMapper;
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
