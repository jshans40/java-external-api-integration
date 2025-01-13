package com.jshans.request;

public enum ApiURL {
    CREATE_USER_REQUEST("http://localhost:8080/api/users");

    final String url;

    ApiURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
