package com.example.onetoone.presentation;

import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.user.commands.CreateUserCommand;
import com.example.onetoone.core.user.commands.GetUserCommand;
import com.example.onetoone.core.user.results.UserResult;
import com.example.onetoone.inrastructure.events.registration.OnRegistrationCompleteEvent;
import com.example.onetoone.inrastructure.service.VerificationService;
import com.example.onetoone.presentation.mapper.UserViewMapper;
import com.example.onetoone.presentation.request.CreateUserRequest;
import com.example.onetoone.presentation.view.UserView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final CommandBus commandBus;
    private final UserViewMapper mapper;
    private final ApplicationEventPublisher eventPublisher;
    private final VerificationService verificationService;

    @PostMapping("/register")
    public UserView create(@Valid @RequestBody CreateUserRequest request){
        log.info("Request for crate user");

        UserResult userResult = commandBus.execute(CreateUserCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .surName(request.getSurName())
                .build());
         eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userResult));

        return mapper.toView(userResult);
    }

    @GetMapping(path = "/register/confirm")
    public String confirmUserRegistration(@RequestParam String token) {
        log.info("Request for confirm user registration");

        return verificationService.confirmationUserRegistration(token);
    }

    @GetMapping("/{id}")
    public UserView get(@PathVariable Long id){
        log.info("Request for get user by id");

        return mapper.toView(commandBus.execute(GetUserCommand.builder()
                .id(id)
                .build()));
    }
}
