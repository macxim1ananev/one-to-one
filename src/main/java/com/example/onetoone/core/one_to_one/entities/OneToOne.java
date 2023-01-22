package com.example.onetoone.core.one_to_one.entities;

import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.technology.entities.Technology;
import com.example.onetoone.core.user.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OneToOne {
    private Long id;
    private User initiator;
    private User opponent;
    private Technology technology;
    private LocalDateTime dateTime;
    private OneToOneLevel level;
    private OneToOneStatus status;
    private String comment;

    public boolean validate(long opponentId){
        if (initiator.getId() != opponentId){
            return true;
        } else {
            throw new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_VALID);
        }
    }
}
