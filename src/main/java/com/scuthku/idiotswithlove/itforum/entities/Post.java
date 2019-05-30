package com.scuthku.idiotswithlove.itforum.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC) /**lombok annotation for no argument constructor.*/
@AllArgsConstructor(access = AccessLevel.PUBLIC) /**lombok annotation for all attributes arguments constructor.*/
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    private int postId;
    @CreatedDate
    private Date postTime;
    private String title;
    private int likeNum;
    private String module;
    private String label;
    private int userId;
    private String filePaths;
    private String postContent;
    @LastModifiedDate
    private Date updateTime;
    private int replyNum;
}
