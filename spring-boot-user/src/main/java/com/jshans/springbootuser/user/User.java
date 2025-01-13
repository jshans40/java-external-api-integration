package com.jshans.springbootuser.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {

    private long id;
    private String name;
    private String password;
    private String email;
    private LocalDateTime createDateTime;

    public User(long id, String name, String password, String email, LocalDateTime createDateTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.createDateTime = createDateTime;
    }
}
