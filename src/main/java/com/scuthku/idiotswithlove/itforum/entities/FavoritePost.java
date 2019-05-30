package com.scuthku.idiotswithlove.itforum.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "favorite")
@DynamicUpdate
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC) /**lombok annotation for no argument constructor.*/
@AllArgsConstructor(access = AccessLevel.PUBLIC) /**lombok annotation for all attributes arguments constructor.*/
public class FavoritePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Integer favoriteId;
    @Column(name = "post_id")
    private Integer postId;
    @Column(name = "user_id")
    private Integer userId;
}
