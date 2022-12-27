package com.example.onetoone.presentation;

import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.technology.commands.AddTechnologyCommand;
import com.example.onetoone.core.technology.commands.GetFilteredAndSortedTechnologyListCommand;
import com.example.onetoone.core.technology.results.TechnologyResult;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.TechnologyViewMapper;
import com.example.onetoone.presentation.request.AddTechnologyRequest;
import com.example.onetoone.presentation.view.TechnologyView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.example.onetoone.presentation.WebUtils.getCriteria;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/technology")
@RequiredArgsConstructor
public class TechnologyController {
    private final TechnologyViewMapper mapper;
    private final CommandBus commandBus;

    @PostMapping
    public TechnologyView add(@Valid @RequestBody AddTechnologyRequest request){
        return mapper.toView(commandBus.execute(AddTechnologyCommand
                .builder()
                .name(request.getName())
                .build()));
    }

    @GetMapping
    public ListView<TechnologyView> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size,
                                           @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                           @RequestParam(required = false, value = "search") String search){
        var searchCriteria = getCriteria(search);

        ResultModelList<TechnologyResult> resultList = commandBus.execute(GetFilteredAndSortedTechnologyListCommand
                .builder()
                .page(page)
                .sort(sort)
                .size(size)
                .criteria(searchCriteria)
                .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems().stream().map(mapper::toView).collect(Collectors.toList()));

    }
}
