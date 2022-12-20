package com.example.onetoone.presentation;

import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.technology.commands.AddTechnologyCommand;
import com.example.onetoone.presentation.mapper.TechnologyViewMapper;
import com.example.onetoone.presentation.request.AddTechnologyRequest;
import com.example.onetoone.presentation.view.TechnologyView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
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
}
