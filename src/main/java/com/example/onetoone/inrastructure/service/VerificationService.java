package com.example.onetoone.inrastructure.service;

public interface VerificationService {
    void createVerificationToken(Long userId, String token);

    String confirmationUserRegistration(String token);
}
