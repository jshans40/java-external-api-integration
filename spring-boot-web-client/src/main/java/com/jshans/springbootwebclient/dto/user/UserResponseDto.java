package com.jshans.springbootwebclient.dto.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    
    private final long id;
    private final String name;
    private final String email;
    private final LocalDateTime createDatetime;

    public UserResponseDto(long id, String name, String email, LocalDateTime createDatetime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDatetime = createDatetime;
    }
}
