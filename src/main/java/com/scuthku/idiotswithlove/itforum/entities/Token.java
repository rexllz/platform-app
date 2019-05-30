package com.scuthku.idiotswithlove.itforum.entities;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicUpdate
/**lombok annotation for automatically building the getters, setters, equals, hashCode and toString.*/
@Data
/**lombok annotation for no argument constructor.*/
@NoArgsConstructor(access = AccessLevel.PUBLIC)
/**lombok annotation for all attributes arguments constructor.*/
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Token {
    private String userId;
    private String value;
}
