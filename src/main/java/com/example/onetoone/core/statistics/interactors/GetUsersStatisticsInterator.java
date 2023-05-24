package com.example.onetoone.core.statistics.interactors;

import com.example.onetoone.core.statistics.commands.GetUserStatisticsCommand;
import com.example.onetoone.core.statistics.results.UserStatisticsResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.UsersStatistics;
import com.example.onetoone.core.statistics.UsersStatisticsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUsersStatisticsInterator implements Interactor<GetUserStatisticsCommand, UserStatisticsResult> {

    private final UsersStatistics usersStatistics;
    private final UsersStatisticsMapper mapper;

    @Override
    public UserStatisticsResult execute(GetUserStatisticsCommand command) {
        log.info("Executing command {}", command);

        var entity = usersStatistics.get(command.getId())
                .orElseThrow(()-> new ServiceException(ServiceException.Exception.USER_STATISTICS_NOT_FOUND, command.getId()));

        return mapper.toResult(entity);
    }
}
