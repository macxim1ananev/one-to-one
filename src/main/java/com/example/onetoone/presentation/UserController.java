package com.example.onetoone.presentation;

import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.user.commands.CreateUserCommand;
import com.example.onetoone.presentation.mapper.UserViewMapper;
import com.example.onetoone.presentation.request.CreateUserRequest;
import com.example.onetoone.presentation.view.UserView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final CommandBus commandBus;
    private final UserViewMapper mapper;

    @PostMapping()
    public UserView create(@Valid @RequestBody CreateUserRequest request){
        return mapper.toView(commandBus.execute(CreateUserCommand.builder()
                .login(request.getLogin())
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .surName(request.getSurName())
                .build()));
    }
}
