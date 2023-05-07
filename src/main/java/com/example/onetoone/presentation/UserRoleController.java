package com.example.onetoone.presentation;

import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.user.commands.GetFilteredAndSortedUserRolesListCommand;
import com.example.onetoone.core.user.commands.GetUserRoleByCodeCommand;
import com.example.onetoone.core.user.entities.Permissions;
import com.example.onetoone.core.user.results.UserRoleResultModel;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.UserRoleViewMapper;
import com.example.onetoone.presentation.view.UserRoleView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.onetoone.presentation.WebUtils.getCriteria;

@Slf4j
@RestController
@RequestMapping("/v1/user-roles")
@RequiredArgsConstructor
public class UserRoleController {

    private final CommandBus commandBus;
    private final UserRoleViewMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_ALL_USER_ROLE + "')")
    public ListView<UserRoleView> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size,
                                         @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                         @RequestParam(required = false, value = "search") String search) {
        log.info("Request for user roles list");

        var searchCriteria = getCriteria(search);
        ResultModelList<UserRoleResultModel> result = commandBus.execute(GetFilteredAndSortedUserRolesListCommand
                .builder()
                .page(page)
                .size(size)
                .sort(sort)
                .criteria(searchCriteria)
                .build());
        return new ListView<>(result.getTotalItems(), mapper.toListView(result.getItems()));
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_USER_ROLE + "')")
    public UserRoleView get(@PathVariable String code) {
        log.info("Request for permission with code {}", code);

        return mapper.toView(commandBus.execute(GetUserRoleByCodeCommand.builder()
                .code(code)
                .build()));
    }
}
