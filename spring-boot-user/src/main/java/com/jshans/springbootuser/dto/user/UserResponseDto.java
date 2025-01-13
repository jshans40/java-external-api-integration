package com.jshans.springbootuser.dto.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    
    private final long id;
    private final String name;
    private final String email;
    private final LocalDateTime createDateTime;

    public UserResponseDto(long id, String name, String email, LocalDateTime createDateTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDateTime = createDateTime;
    }
}
