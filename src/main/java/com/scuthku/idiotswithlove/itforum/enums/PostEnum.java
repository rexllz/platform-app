package com.scuthku.idiotswithlove.itforum.enums;

import lombok.Getter;

@Getter
public enum PostEnum {
    NULL_TITLE(20,"no post title"),
    NULL_AUTHOR(19,"no post author");

    private  Integer code;

    private  String message;

    PostEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}


