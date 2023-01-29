package com.example.onetoone.inrastructure.input.data.repositories;

import com.example.onetoone.inrastructure.input.data.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
