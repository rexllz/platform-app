package com.scuthku.idiotswithlove.itforum.handlers.exceptions;

import com.scuthku.idiotswithlove.itforum.enums.UserEnum;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private Integer code;

    public UserException(UserEnum userEnum){
        super(userEnum.getMessage());

        this.code = userEnum.getCode();

    };
}