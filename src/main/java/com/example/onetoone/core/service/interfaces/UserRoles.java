package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.user.entities.UserRole;

import java.util.Optional;
import java.util.Set;

public interface UserRoles {

    EntityList<UserRole> getAll(ListFilter filter);

    Optional<UserRole> findById(Long id);

    Optional<UserRole> findByCode(String code);

    Set<UserRole> findByGroupName(String groupName);
}
