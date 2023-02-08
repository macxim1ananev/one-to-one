package com.example.onetoone.presentation;

import com.example.onetoone.core.one_to_one.commands.*;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.OneToOneViewMapper;
import com.example.onetoone.presentation.request.AcceptOneToOneRequest;
import com.example.onetoone.presentation.request.CloseOneToOneRequest;
import com.example.onetoone.presentation.request.CreateOneToOneRequest;
import com.example.onetoone.presentation.view.OneToOneView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.example.onetoone.presentation.WebUtils.getCriteria;
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/v1/one-to-one")
@RequiredArgsConstructor
public class OneToOneController {

    private final CommandBus commandBus;
    private final OneToOneViewMapper mapper;

    @PostMapping()
    public OneToOneView create(@Valid @RequestBody CreateOneToOneRequest request){
        log.info("Request for crate one to one");

        return mapper.toView(commandBus.execute(CreateOneToOneCommand.builder()
                .initiatorId(request.getInitiatorId())
                .technologyId(request.getTechnologyId())
                .levelId(request.getLevelId())
                .dateTime(request.getDateTime())
                .comment(request.getComment())
                .build()));
    }

    @GetMapping("/{id}")
    public OneToOneView get(@PathVariable Long id){
        log.info("Request for get one to one");

        return mapper.toView(commandBus.execute(GetOneToOneCommand
                .builder()
                .id(id)
                .build()));
    }

    @PutMapping()
    public OneToOneView accept(@Valid @RequestBody AcceptOneToOneRequest request){
        log.info("Request for accept one to one");

        return mapper.toView(commandBus.execute(AcceptOneToOneCommand.builder()
                .opponentId(request.getOpponentId())
                .oneToOneId(request.getOneToOneId())
                .build()));
    }

    @PutMapping("/{id}/close")
    public OneToOneView close(@PathVariable long id, @Valid @RequestBody CloseOneToOneRequest request){
        log.info("Request for close one to one");

        return mapper.toView(commandBus.execute(CloseOneToOneCommand.builder()
                .id(id)
                .authorId(request.getAuthorId())
                .opponentId(request.getOpponentId())
                .build()));
    }

    @GetMapping
    public ListView<OneToOneView> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size,
                                         @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                         @RequestParam(required = false, value = "search") String search){
        log.info("Request for get all one to one");

        var searchCriteria = getCriteria(search);
        ResultModelList<OneToOneResult> resultList = commandBus.execute(GetFilteredAndSortedOneToOneListCommand
                .builder()
                .page(page)
                .sort(sort)
                .size(size)
                .criteria(searchCriteria)
                .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems().stream().map(mapper::toView).collect(Collectors.toList()));
    }
}
