package com.example.onetoone.presentation;

import com.example.onetoone.core.one_to_one.commands.AcceptOneToOneCommand;
import com.example.onetoone.core.one_to_one.commands.CloseOneToOneCommand;
import com.example.onetoone.core.one_to_one.commands.CreateOneToOneCommand;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.presentation.mapper.OneToOneViewMapper;
import com.example.onetoone.presentation.request.AcceptOneToOneRequest;
import com.example.onetoone.presentation.request.CloseOneToOneRequest;
import com.example.onetoone.presentation.request.CreateOneToOneRequest;
import com.example.onetoone.presentation.view.OneToOneView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/one-to-one")
@RequiredArgsConstructor
public class OneToOneController {

    private final CommandBus commandBus;
    private final OneToOneViewMapper mapper;

    @PostMapping()
    public OneToOneView create(@Valid @RequestBody CreateOneToOneRequest request){
        return mapper.toView(commandBus.execute(CreateOneToOneCommand.builder()
                .initiatorId(request.getInitiatorId())
                .programmingLanguage(request.getProgrammingLanguage())
                        .dateTime(request.getDateTime())
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
}
