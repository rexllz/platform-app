package com.scuthku.idiotswithlove.itforum.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
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
public class Reply {
    @Id
    private int replyId;
    private int postId;
    private int userId;
    private int floor;
    private String replyContent;
    private int replyWho;
    @CreatedDate
    private Date replyTime;
}
