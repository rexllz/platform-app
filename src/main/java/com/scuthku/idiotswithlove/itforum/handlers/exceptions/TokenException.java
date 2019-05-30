package com.scuthku.idiotswithlove.itforum.handlers.exceptions;

import com.scuthku.idiotswithlove.itforum.enums.TokenEnum;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {
    private Integer code;

    public TokenException(TokenEnum tokenEnum){
        super(tokenEnum.getMessage());

        this.code = tokenEnum.getCode();

    };
}
