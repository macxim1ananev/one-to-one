package com.example.onetoone.inrastructure.output.email;

import com.example.onetoone.core.user.results.UserResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String email;
    private Long userId;

    public OnRegistrationCompleteEvent(UserResult user) {
        super(user);
        email = user.getEmail();
        userId = user.getId();
    }
}