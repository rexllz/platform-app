package com.scuthku.idiotswithlove.itforum.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDTO {
    private int replyId;
    private int postId;
    //    private int userId;
    private String authorNick;
    private int floor;
    private String replyContent;
//    private int replyWho;

    private String replyWhoNick;

    private Date replyTime;
}