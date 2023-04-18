package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.UserPermissions;
import com.example.onetoone.core.user.UserPermissionsMapper;
import com.example.onetoone.core.user.commands.GetUserPermissionByCodeCommand;
import com.example.onetoone.core.user.entities.UserPermission;
import com.example.onetoone.core.user.results.UserPermissionResultModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserPermissionsByCodeInteractor implements Interactor<GetUserPermissionByCodeCommand, UserPermissionResultModel> {

    private final UserPermissions userPermissions;
    private final UserPermissionsMapper mapper;

    @Override
    public UserPermissionResultModel execute(GetUserPermissionByCodeCommand command) {
        log.info("Executing command {}", command);

        UserPermission userPermission = userPermissions.findByCode(command.getCode())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_PERMISSION_NOT_FOUND, command.getCode()));
        return mapper.toModel(userPermission);
    }
}
