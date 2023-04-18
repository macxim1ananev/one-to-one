package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.UserRoleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRoleModel, Long> {

    Page<UserRoleModel> findAll(Specification<UserRoleModel> specification, Pageable pageable);

    Optional<UserRoleModel> findByCode(String code);

    Set<UserRoleModel> findByGroupsName(String groupName);
}
