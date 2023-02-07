package com.example.onetoone.presentation;

import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.technology.commands.AddTechnologyCommand;
import com.example.onetoone.core.technology.commands.GetFilteredAndSortedTechnologyListCommand;
import com.example.onetoone.core.technology.commands.GetTechnologyCommand;
import com.example.onetoone.core.technology.results.TechnologyResult;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.TechnologyViewMapper;
import com.example.onetoone.presentation.request.AddTechnologyRequest;
import com.example.onetoone.presentation.view.TechnologyView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.example.onetoone.presentation.WebUtils.getCriteria;
@Slf4j
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/v1/technology")
@RequiredArgsConstructor
public class TechnologyController {
    private final TechnologyViewMapper mapper;
    private final CommandBus commandBus;

    @PostMapping
    public TechnologyView add(@Valid @RequestBody AddTechnologyRequest request){
        log.info("Request for crate technology");

        return mapper.toView(commandBus.execute(AddTechnologyCommand
                .builder()
                .name(request.getName())
                .build()));
    }

    @GetMapping("/{id}")
    public TechnologyView get(@PathVariable Long id){
        log.info("Request for get technology");

        return mapper.toView(commandBus.execute(GetTechnologyCommand
                .builder()
                .id(id)
                .build()));
    }

    @GetMapping
    public ListView<TechnologyView> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size,
                                           @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                           @RequestParam(required = false, value = "search") String search){
        log.info("Request for get all technolgy");
        var searchCriteria = getCriteria(search);

        ResultModelList<TechnologyResult> resultList = commandBus.execute(GetFilteredAndSortedTechnologyListCommand
                .builder()
                .page(page)
                .sort(sort)
                .size(size)
                .criteria(searchCriteria)
                .build());

        return new ListView<>(resultList.getTotalItems(),
                resultList.getItems().stream().map(mapper::toView).collect(Collectors.toList()));
    }
}
