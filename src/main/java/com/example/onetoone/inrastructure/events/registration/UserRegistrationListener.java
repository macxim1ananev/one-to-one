package com.example.onetoone.inrastructure.events.registration;

import com.example.onetoone.inrastructure.output.email.MailSenderService;
import com.example.onetoone.inrastructure.output.email.Message;
import com.example.onetoone.inrastructure.service.verification.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserRegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MailSenderService mailSenderService;
    private final VerificationService verificationService;
    @Value("${server.ip}")
    private String ip;
    @Value("${server.port}")
    private String port;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    @SneakyThrows
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        String token = UUID.randomUUID().toString();
        verificationService.createVerificationToken(event.getUserId(), token);


        String recipientAddress = event.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = "/one-to-one/api/v1/user/register/confirm/?token=" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(Message.CONFIRM_REGISTRATION_FOR_NEW_USER + "\r\n" +
                ip + ":" + port + confirmationUrl);
        mailSenderService.send(email);
    }
}
