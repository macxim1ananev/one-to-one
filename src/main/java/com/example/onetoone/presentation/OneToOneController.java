package com.example.onetoone.presentation;

import com.example.onetoone.core.one_to_one.commands.AcceptOneToOneCommand;
import com.example.onetoone.core.one_to_one.commands.CloseOneToOneCommand;
import com.example.onetoone.core.one_to_one.commands.CreateOneToOneCommand;
import com.example.onetoone.core.one_to_one.commands.GetFilteredAndSortedOneToOneListCommand;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.example.onetoone.presentation.WebUtils.getCriteria;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/one-to-one")
@RequiredArgsConstructor
public class OneToOneController {

    private final CommandBus commandBus;
    private final OneToOneViewMapper mapper;

    @PostMapping()
    public OneToOneView create(@Valid @RequestBody CreateOneToOneRequest request){
        return mapper.toView(commandBus.execute(CreateOneToOneCommand.builder()
                .initiatorId(request.getInitiatorId())
                .technologyId(request.getTechnologyId())
                .dateTime(request.getDateTime())
                .comment(request.getComment())
                .build()));
    }

    @PutMapping()
    public OneToOneView accept(@Valid @RequestBody AcceptOneToOneRequest request){
        return mapper.toView(commandBus.execute(AcceptOneToOneCommand.builder()
                .opponentId(request.getOpponentId())
                .oneToOneId(request.getOneToOneId())
                .build()));
    }

    @PutMapping("/{id}/close")
    public OneToOneView close(@PathVariable long id, @Valid @RequestBody CloseOneToOneRequest request){

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
