package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.service.interfaces.UserRoles;
import com.example.onetoone.core.user.entities.UserRole;
import com.example.onetoone.inrastructure.output.data.FilteringAndSortingAdapter;
import com.example.onetoone.inrastructure.output.data.mappers.UserRoleModelMapper;
import com.example.onetoone.inrastructure.output.data.models.UserRoleModel;
import com.example.onetoone.inrastructure.output.data.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRolesAdapter extends FilteringAndSortingAdapter<UserRoleModel> implements UserRoles {

    private final UserRoleRepository repository;
    private final UserRoleModelMapper mapper;

    @Override
    public EntityList<UserRole> getAll(ListFilter filter) {
        var page = repository.findAll(getSpecification(filter), getPageable(filter))
                .map(mapper::toEntity);
        return new EntityList<>(page.getTotalElements(), page.getContent());
    }

    @Override
    public Optional<UserRole> findById(Long id) {
        return repository.findById(id).map(mapper::toEntity);
    }

    @Override
    public Optional<UserRole> findByCode(String code) {
        return repository.findByCode(code).map(mapper::toEntity);
    }
}
