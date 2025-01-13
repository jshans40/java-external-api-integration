package com.jshans.user;


import java.time.LocalDateTime;

public class User {

    private long id;
    private String name;
    private String password;
    private String email;
    private LocalDateTime createDateTime;

    public User() {
    }

    public User(long id, String name, String password, String email, LocalDateTime createDateTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.createDateTime = createDateTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
}
