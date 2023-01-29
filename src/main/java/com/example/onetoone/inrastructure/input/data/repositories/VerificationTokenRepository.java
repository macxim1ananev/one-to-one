package com.example.onetoone.inrastructure.input.data.repositories;

import com.example.onetoone.inrastructure.input.data.models.VerificationTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenModel, Long> {
}
