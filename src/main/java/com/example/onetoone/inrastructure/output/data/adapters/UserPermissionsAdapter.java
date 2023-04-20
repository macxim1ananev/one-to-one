package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.service.interfaces.UserPermissions;
import com.example.onetoone.core.user.entities.UserPermission;
import com.example.onetoone.inrastructure.output.data.FilteringAndSortingAdapter;
import com.example.onetoone.inrastructure.output.data.mappers.UserPermissionModelMapper;
import com.example.onetoone.inrastructure.output.data.models.UserPermissionModel;
import com.example.onetoone.inrastructure.output.data.repositories.UserPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserPermissionsAdapter extends FilteringAndSortingAdapter<UserPermissionModel> implements UserPermissions {

    private final UserPermissionRepository repository;
    private final UserPermissionModelMapper mapper;

    @Override
    public EntityList<UserPermission> getAll(ListFilter filter) {
        var page = repository.findAll(getSpecification(filter), getPageable(filter))
                .map(mapper::toEntity);
        return new EntityList<>(page.getTotalElements(), page.getContent());
    }

    @Override
    public Optional<UserPermission> findById(Long id) {
        return repository.findById(id).map(mapper::toEntity);
    }

    @Override
    public Optional<UserPermission> findByCode(String code) {
        return repository.findByCode(code).map(mapper::toEntity);
    }

    @Override
    public Set<UserPermission> findByRoleCode(String roleCode) {
        return mapper.toEntity(repository.findByRolesCode(roleCode));
    }
}
