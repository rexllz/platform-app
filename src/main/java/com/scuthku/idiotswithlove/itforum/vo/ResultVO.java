package com.scuthku.idiotswithlove.itforum.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;
}
