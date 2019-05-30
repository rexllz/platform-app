package com.scuthku.idiotswithlove.itforum.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@DynamicUpdate
/**lombok annotation for automatically building the getters, setters, equals, hashCode and toString.*/
@Data
/**lombok annotation for no argument constructor.*/
@NoArgsConstructor(access = AccessLevel.PUBLIC)
/**lombok annotation for all attributes arguments constructor.*/
public class UserDTO implements Serializable {
    private Integer userId;
    private String username;
    private String email;
    private String nickname;
    private String password;
    private String signature;
    private String image;
    private Integer xp;
    private Integer age;
    private String gender;
}
