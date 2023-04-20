package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.GetListInteractor;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.UserRoles;
import com.example.onetoone.core.user.UserRoleMapper;
import com.example.onetoone.core.user.commands.GetFilteredAndSortedUserRolesListCommand;
import com.example.onetoone.core.user.entities.UserRole;
import com.example.onetoone.core.user.results.UserRoleResultModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
final class GetUserRoleListInteractor extends GetListInteractor
        implements Interactor<GetFilteredAndSortedUserRolesListCommand, ResultModelList<UserRoleResultModel>> {

    private final UserRoles userRoles;
    private final UserRoleMapper mapper;

    @Override
    public ResultModelList<UserRoleResultModel> execute(GetFilteredAndSortedUserRolesListCommand command) {
        log.info("Executing command {}", command);

        var filter = getListFilter(command, UserRole.class);
        var entityList = userRoles.getAll(filter);
        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream()
                        .map(mapper::toModel)
                        .collect(Collectors.toList()));
    }
}
