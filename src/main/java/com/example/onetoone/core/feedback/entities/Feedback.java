package com.example.onetoone.core.feedback.entities;

import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.user.entities.User;
import lombok.Data;

import java.util.Objects;

@Data
public class Feedback {
    private Long id;
    private OneToOne oneToOne;
    private User author;
    private User recipient;
    private String message;

    public boolean isValid(){
        if (Objects.equals(author.getId(), recipient.getId())){
            throw new ServiceException(ServiceException.Exception.FEEDBACK_NOT_VALID);
        } else {
            return true;
        }
    }
}
