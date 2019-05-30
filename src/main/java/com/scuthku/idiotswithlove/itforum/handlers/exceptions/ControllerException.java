package com.scuthku.idiotswithlove.itforum.handlers.exceptions;

import com.scuthku.idiotswithlove.itforum.enums.ControllerEnum;
import com.scuthku.idiotswithlove.itforum.enums.PostEnum;
import lombok.Getter;

@Getter
public class ControllerException extends RuntimeException {
    private Integer code;

    public ControllerException(ControllerEnum controllerEnum){
        super(controllerEnum.getMessage());
        this.code = controllerEnum.getCode();
    };
}
