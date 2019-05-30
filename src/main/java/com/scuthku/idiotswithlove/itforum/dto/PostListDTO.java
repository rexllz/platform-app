package com.scuthku.idiotswithlove.itforum.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostListDTO {

    private int postId;
    private Date postTime;
    private String title;
    private int likeNum;
    private String module;
    private String label;
    //    private int userId;
    private String authorNickname;
    private Date updateTime;
    private int replyNum;
}