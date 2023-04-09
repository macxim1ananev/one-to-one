package com.example.onetoone.presentation;

import com.example.onetoone.core.feedback.commands.statistics.GetFilteredAndSortedUserStatisticsListCommand;
import com.example.onetoone.core.feedback.commands.statistics.GetFullUserStatisticsCommand;
import com.example.onetoone.core.feedback.commands.statistics.GetUserStatisticsCommand;
import com.example.onetoone.core.feedback.commands.statistics.GetUserTechnologyStatisticsCommand;
import com.example.onetoone.core.feedback.results.statistics.FullUserStatisticsResult;
import com.example.onetoone.core.feedback.results.statistics.UserStatisticsResult;
import com.example.onetoone.core.feedback.results.statistics.UserTechnologyStatisticsResult;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.FullUsersStatisticsViewMapper;
import com.example.onetoone.presentation.mapper.UsersStatisticsViewMapper;
import com.example.onetoone.presentation.view.FullUserStatisticsView;
import com.example.onetoone.presentation.view.UserStatisticsView;
import com.example.onetoone.presentation.view.UserTechnologyStatisticsView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public UserStatisticsView getUserStatistics(@PathVariable Long userId){
        log.info("Request for get user statistics");

        return statisticsViewMapper.toView(commandBus.execute(GetUserStatisticsCommand
                .builder()
                .id(userId)
                .build()));
    }

    @GetMapping("/{userId}/full-statistics")
    public ListView<FullUserStatisticsView> getFullUserStatistics(@PathVariable Long userId){
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
    public ListView<UserTechnologyStatisticsView> getUserTechnologyStatistics(@PathVariable Long userId){
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
/**
 * TODO
 @GetMapping("/technology-statistics") public ListView<UserTechnologyStatisticsView> getAllUserTechnologyStatistics(@RequestParam(required = false, defaultValue = "0") int page,
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
 */
}
