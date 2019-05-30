package com.scuthku.idiotswithlove.itforum.enums;

import lombok.Getter;

@Getter
public enum TokenEnum {
    FAIL_TO_CREATE_TOKEN(5,"fail to create the token"),
    NO_EXISTED_TOKEN(4,"no such token"),
    WRAP_WRONG(3, "wrap wrong")
    ;

    private  Integer code;

    private  String message;

    TokenEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
