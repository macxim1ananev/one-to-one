package com.example.onetoone.inrastructure.output.email;

import org.springframework.mail.SimpleMailMessage;

public interface MailSenderService {

    void send(SimpleMailMessage mailMessage);

}
