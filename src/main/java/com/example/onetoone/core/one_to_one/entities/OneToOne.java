package com.example.onetoone.core.one_to_one.entities;

import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.user.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OneToOne {
    private Long id;
    private User initiator;
    private User opponent;
    private ProgrammingLanguage programmingLanguage;
    private LocalDateTime dateTime;
    private OneToOneStatus status;

    public boolean validate(long opponentId){
        if (initiator.getId() != opponentId){
            return true;
        } else {
            throw new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_VALID);
        }
    }
}
