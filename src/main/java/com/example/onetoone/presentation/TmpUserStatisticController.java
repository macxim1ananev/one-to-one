package com.example.onetoone.presentation;

import com.example.onetoone.core.feedback.commands.statistics.GetFilteredAndSortedUserStatisticsListCommand;
import com.example.onetoone.core.feedback.results.statistics.UserStatisticsResult;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.UsersStatisticsViewMapper;
import com.example.onetoone.presentation.view.UserStatisticsView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.example.onetoone.presentation.WebUtils.getCriteria;

@Slf4j
@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
public class TmpUserStatisticController {
    private final CommandBus commandBus;
    private final UsersStatisticsViewMapper statisticsViewMapper;

    @GetMapping("statistics/")
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
