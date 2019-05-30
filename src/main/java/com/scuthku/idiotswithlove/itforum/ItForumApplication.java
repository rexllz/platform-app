package com.scuthku.idiotswithlove.itforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ItForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItForumApplication.class, args);
    }
}
