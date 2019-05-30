package com.scuthku.idiotswithlove.itforum.enums;

import lombok.Getter;

@Getter
public enum UserEnum {
    DUPLICATE_NAME(10, "this name is occupied"),
    DUPLICATE_EMAIL(9, "this email is occupied"),
    USER_NOT_EXIST(8, "the user is not exist"),
    SALT_INIT_ERROR(7, "it is failed to create the salt."),
    ;

    private  Integer code;

    private  String message;

    UserEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
