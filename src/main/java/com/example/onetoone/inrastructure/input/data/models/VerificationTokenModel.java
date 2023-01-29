package com.example.onetoone.inrastructure.input.data.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "verification_token")
public class VerificationTokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Long userId;
    private LocalDateTime expiryDate;
}
