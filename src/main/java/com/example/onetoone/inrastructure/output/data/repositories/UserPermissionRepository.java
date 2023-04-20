package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.UserPermissionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserPermissionRepository extends JpaRepository<UserPermissionModel, Long> {

    Page<UserPermissionModel> findAll(Specification<UserPermissionModel> specification, Pageable pageable);

    Optional<UserPermissionModel> findByCode(String code);

    Set<UserPermissionModel> findByRolesCode(String code);
}
