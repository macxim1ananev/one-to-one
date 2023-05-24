package com.example.onetoone.presentation;

import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.user.commands.GetFilteredAndSortedUserPermissionsListCommand;
import com.example.onetoone.core.user.commands.GetUserPermissionByCodeCommand;
import com.example.onetoone.core.user.entities.Permissions;
import com.example.onetoone.core.user.results.UserPermissionResultModel;
import com.example.onetoone.presentation.view.common.ListView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.onetoone.presentation.WebUtils.getCriteria;

@Slf4j
@RestController
@RequestMapping("/v1/user-permissions")
@RequiredArgsConstructor
public class UserPermissionController {

    private final CommandBus commandBus;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_ALL_USER_PERMISSION + "')")
    public ListView<UserPermissionResultModel> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                                      @RequestParam(required = false, defaultValue = "10") int size,
                                                      @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                                      @RequestParam(required = false, value = "search") String search) {
        log.info("Request for user permissions list");

        var searchCriteria = getCriteria(search);
        ResultModelList<UserPermissionResultModel> result = commandBus.execute(GetFilteredAndSortedUserPermissionsListCommand
                .builder()
                .page(page)
                .size(size)
                .sort(sort)
                .criteria(searchCriteria)
                .build());
        return new ListView<>(result.getTotalItems(), result.getItems());
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_USER_PERMISSION + "')")
    public UserPermissionResultModel get(@PathVariable String code) {
        log.info("Request for permission with code {}", code);

        return commandBus.execute(GetUserPermissionByCodeCommand.builder()
                .code(code)
                .build());
    }
}
