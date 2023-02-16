package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.VerificationTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenModel, Long> {
    @Query("from VerificationTokenModel vereficationToken where vereficationToken.token = :token")
    Optional<VerificationTokenModel> getUserVerificationToken(@Param("token") String token);
}
