package com.jshans.request.user;

import com.jshans.request.ApiRequest;

public class UserSaveRequest implements ApiRequest {

    private long id;
    private String name;
    private String password;
    private String email;

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

    public UserSaveRequest(long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toJsonString() {
        return "{"
                + "        \"id\":\"" + id + "\""
                + ",         \"name\":\"" + name + "\""
                + ",         \"password\":\"" + password + "\""
                + ",         \"email\":\"" + email + "\""
                + "}";
    }

}
