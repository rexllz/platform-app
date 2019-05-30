package com.scuthku.idiotswithlove.itforum.enums;

import lombok.Getter;

@Getter
public enum ControllerEnum {
    //UserEnum
    DUPLICATE_NAME(1, "this name is occupied"),
    DUPLICATE_EMAIL(2, "this email is occupied"),
    USER_NOT_EXIST(3, "the user is not exist"),
    SALT_INIT_ERROR(4, "it is failed to create the salt."),
    NO_PICTURE_FOR_USER(5, "lose the pic or usrid"),
    FAIL_TO_SAVE_PICTURE(6, "it is failed to save the picture."),
    NOT_ALLOW_PARAMETERS(7, "the parameters are not allowed."),
    PASSWORD_WRONG(8, "the password is wrong"),

    //PostEnum
    NULL_TITLE(101,"no post title"),
    NULL_MODULE(102,"no post MODULE"),
    ZERO_SIZE(103, "size 0 is not allowed."),
    NO_SUCH_POST(104, "the post is not existed."),
    FAVOR_EXISTED(105, "the post is existed in the favorite."),

    //TokenEnum
    FAIL_TO_CREATE_TOKEN(201,"fail to create the token"),
    NO_EXISTED_TOKEN(202,"no such token"),
    WRAP_WRONG(203, "wrap wrong"),
    TOKEN_INVALID(204, "the token is invalid."),

    //ReplyEnum
    NO_SUCH_REPLY(301, "no such reply.")
    ;

    private  Integer code;

    private  String message;

    ControllerEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
