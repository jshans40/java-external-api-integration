package com.jshans.springbootuser.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSaveRequestDto {

    private long id;
    private String name;
    private String password;
    private String email;

}
