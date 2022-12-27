package com.example.onetoone.presentation;

import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.technology.commands.AddTechnologyCommand;
import com.example.onetoone.presentation.mapper.TechnologyViewMapper;
import com.example.onetoone.presentation.request.AddTechnologyRequest;
import com.example.onetoone.presentation.view.TechnologyView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
