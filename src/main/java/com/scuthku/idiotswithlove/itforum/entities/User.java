package com.scuthku.idiotswithlove.itforum.entities;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * USER ENTITY
 */

@Entity /**JPA annotation for distinguishing the type of spring data module for repository.*/
@Table(name = "user") /**refer class user to table user.*/
@DynamicUpdate
@Data /**lombok annotation for automatically building the getters, setters, equals, hashCode and toString.*/
@NoArgsConstructor(access = AccessLevel.PUBLIC) /**lombok annotation for no argument constructor.*/
@AllArgsConstructor(access = AccessLevel.PUBLIC) /**lombok annotation for all attributes arguments constructor.*/
public class User {
    /**JPA annotation for class key.*/
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "signature")
    private String signature="I love IT.";

    @Column(name = "image")
    private String image;

    @Column(name = "xp")
    private Integer xp;

    @Column(name = "age")
    private Integer age;

    @Column(name = "hash")
    private String hash;

    @Column(name = "salt")
    private String salt;

    @Column(name = "gender")
    private String gender;

}