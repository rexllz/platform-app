package com.scuthku.idiotswithlove.itforum.dto;

import lombok.Data;

@Data
public class ReplyPostDTO {


    private int postId;
    private int userId;

    private String replyContent;
    private int replyWho;

}
