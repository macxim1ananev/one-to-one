package com.example.onetoone.presentation;

import com.example.onetoone.core.statistics.command.GetFilteredAndSortedUserStatisticsListCommand;
import com.example.onetoone.core.statistics.command.GetFilteredAndSortedUserTechnologyStatisticsListCommand;
import com.example.onetoone.core.statistics.command.GetFullUserStatisticsCommand;
import com.example.onetoone.core.statistics.command.GetUserStatisticsCommand;
import com.example.onetoone.core.statistics.command.GetUserTechnologyStatisticsCommand;
import com.example.onetoone.core.statistics.result.FullUserStatisticsResult;
import com.example.onetoone.core.statistics.result.UserStatisticsResult;
import com.example.onetoone.core.statistics.result.UserTechnologyStatisticsResult;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.user.entities.Permissions;
import com.example.onetoone.presentation.view.common.ListView;
import com.example.onetoone.presentation.mapper.FullUsersStatisticsViewMapper;
import com.example.onetoone.presentation.mapper.UsersStatisticsViewMapper;
import com.example.onetoone.presentation.view.FullUserStatisticsView;
import com.example.onetoone.presentation.view.UserStatisticsView;
import com.example.onetoone.presentation.view.UserTechnologyStatisticsView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.example.onetoone.presentation.WebUtils.getCriteria;

@Slf4j
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final CommandBus commandBus;
    private final UsersStatisticsViewMapper statisticsViewMapper;
    private final FullUsersStatisticsViewMapper fullStatisticsViewMapper;

    @GetMapping
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_ALL_USER_STATISTICS + "')")
    public ListView<UserStatisticsView> getAllUserStatistics(@RequestParam(required = false, defaultValue = "0") int page,
                                                             @RequestParam(required = false, defaultValue = "10") int size,
                                                             @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                                             @RequestParam(required = false, value = "search") String search) {
        log.info("Request for get all user statistics");

        var searchCriteria = getCriteria(search);
        ResultModelList<UserStatisticsResult> resultList = commandBus.execute(GetFilteredAndSortedUserStatisticsListCommand
                .builder()
                .page(page)
                .sort(sort)
                .size(size)
                .criteria(searchCriteria)
                .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems().stream().map(statisticsViewMapper::toView).collect(Collectors.toList()));

    }

    @GetMapping("/{userId}")
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_USER_STATISTICS + "')")
    public UserStatisticsView getUserStatistics(@PathVariable Long userId) {
        log.info("Request for get user statistics");

        return statisticsViewMapper.toView(commandBus.execute(GetUserStatisticsCommand
                .builder()
                .id(userId)
                .build()));
    }

    @GetMapping("/{userId}/full-statistics")
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_FULL_USER_STATISTICS + "')")
    public ListView<FullUserStatisticsView> getFullUserStatistics(@PathVariable Long userId) {
        log.info("Request for get full user statistics");

        ResultModelList<FullUserStatisticsResult> resultList = commandBus.execute(GetFullUserStatisticsCommand
                .builder()
                .id(userId)
                .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems()
                .stream()
                .map(fullStatisticsViewMapper::toView)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{userId}/technology-statistics")
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_USER_TECHNOLOGY_STATISTICS + "')")
    public ListView<UserTechnologyStatisticsView> getUserTechnologyStatistics(@PathVariable Long userId) {
        log.info("Request for get user technology statistics");

        ResultModelList<UserTechnologyStatisticsResult> resultList = commandBus.execute(GetUserTechnologyStatisticsCommand
                .builder()
                .id(userId)
                .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems()
                .stream()
                .map(fullStatisticsViewMapper::toTechnologyStatisticsView)
                .collect(Collectors.toList()));
    }

    @GetMapping("/technology-statistics")
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_ALL_USER_TECHNOLOGY_STATISTICS + "')")
    public ListView<UserTechnologyStatisticsView> getAllUserTechnologyStatistics(@RequestParam(required = false, defaultValue = "0") int page,
                                                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                                                 @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                                                                 @RequestParam(required = false, value = "search") String search) {

        log.info("Request for get all user technology statistics");

        var searchCriteria = getCriteria(search);
        ResultModelList<UserTechnologyStatisticsResult> resultList = commandBus.execute(GetFilteredAndSortedUserTechnologyStatisticsListCommand
                .builder()
                .page(page)
                .sort(sort)
                .size(size)
                .criteria(searchCriteria)
                .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems().stream().map(fullStatisticsViewMapper::toTechnologyStatisticsView).collect(Collectors.toList()));

    }
}
