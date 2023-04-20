package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.user.entities.UserPermission;

import java.util.Optional;
import java.util.Set;

public interface UserPermissions {

    EntityList<UserPermission> getAll(ListFilter filter);

    Optional<UserPermission> findById(Long id);

    Optional<UserPermission> findByCode(String code);

    Set<UserPermission> findByRoleCode(String roleCode);
}
