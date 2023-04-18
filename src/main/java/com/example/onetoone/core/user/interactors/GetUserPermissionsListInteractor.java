package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.GetListInteractor;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.UserPermissions;
import com.example.onetoone.core.user.UserPermissionsMapper;
import com.example.onetoone.core.user.commands.GetFilteredAndSortedUserPermissionsListCommand;
import com.example.onetoone.core.user.entities.UserPermission;
import com.example.onetoone.core.user.results.UserPermissionResultModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserPermissionsListInteractor extends GetListInteractor implements Interactor<GetFilteredAndSortedUserPermissionsListCommand,
        ResultModelList<UserPermissionResultModel>> {

    private final UserPermissions userPermissions;
    private final UserPermissionsMapper mapper;

    @Override
    public ResultModelList<UserPermissionResultModel> execute(GetFilteredAndSortedUserPermissionsListCommand command) {
        log.info("Executing command {}", command);

        var filter = getListFilter(command, UserPermission.class);
        var entityList = userPermissions.getAll(filter);
        return new ResultModelList<>(entityList.getTotalItems(), entityList.getItems().stream().map(mapper::toModel).collect(Collectors.toList()));
    }
}
