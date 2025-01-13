package com.jshans.springbootwebclient.model.api;

public enum ApiEndpoint {
    USER_POST("http://127.0.0.1:8080/api/users"),
    USER_ONE_GET("http://127.0.0.1:8080/api/users/{userId}"),
    USER_ALL_GET("http://127.0.0.1:8080/api/users"),
    ;

    final String uri;
    ApiEndpoint(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
