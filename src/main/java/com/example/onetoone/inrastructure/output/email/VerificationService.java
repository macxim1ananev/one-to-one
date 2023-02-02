package com.example.onetoone.inrastructure.output.email;

public interface VerificationService {
    void createVerificationToken(Long userId, String token);

    String confirmationUserRegistration(String token);
}
