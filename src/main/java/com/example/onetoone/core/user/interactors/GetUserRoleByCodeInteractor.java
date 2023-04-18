package com.example.onetoone.core.user.interactors;

import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.UserRoles;
import com.example.onetoone.core.user.UserRoleMapper;
import com.example.onetoone.core.user.commands.GetUserRoleByCodeCommand;
import com.example.onetoone.core.user.entities.UserRole;
import com.example.onetoone.core.user.results.UserRoleResultModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserRoleByCodeInteractor implements Interactor<GetUserRoleByCodeCommand, UserRoleResultModel> {

    private final UserRoles userRoles;
    private final UserRoleMapper mapper;

    @Override
    public UserRoleResultModel execute(GetUserRoleByCodeCommand command) {
        log.info("Executing command {}", command);

        UserRole userRole = userRoles.findByCode(command.getCode())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_ROLE_NOT_FOUND, command.getCode()));
        return mapper.toModel(userRole);
    }
}
