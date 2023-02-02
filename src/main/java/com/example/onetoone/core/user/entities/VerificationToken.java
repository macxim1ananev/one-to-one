package com.example.onetoone.core.user.entities;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VerificationToken {
    private Long id;

    private String token;

    private Long userId;
    private LocalDateTime expiryDate;

    public void setExpirationDate(Long expiryTimeInMinutes) {
        this.expiryDate = LocalDateTime.now().plusMinutes(expiryTimeInMinutes);
    }
}
