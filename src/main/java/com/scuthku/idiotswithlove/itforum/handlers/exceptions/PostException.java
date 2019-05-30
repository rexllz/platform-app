package com.scuthku.idiotswithlove.itforum.handlers.exceptions;

import com.scuthku.idiotswithlove.itforum.enums.PostEnum;
import lombok.Getter;

@Getter
public class PostException extends RuntimeException {
    private Integer code;

    public PostException(PostEnum postEnum){
        super(postEnum.getMessage());

        this.code = postEnum.getCode();

    };
}


