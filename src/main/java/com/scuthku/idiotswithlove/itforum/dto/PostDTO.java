package com.scuthku.idiotswithlove.itforum.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import java.util.Date;
@Data
public class PostDTO {

public PostDTO(){};
    private int postId;

    private Date postTime;
    private String title;
    private int likeNum;
    private String module;
    private String label;
//    private int userId;
    private String authorName;
    private String filePaths;
    private String postContent;
    private Date updateTime;
    private int replyNum;
}
