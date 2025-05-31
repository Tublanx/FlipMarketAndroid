package com.lgh.flipmarketandroid.dto.user;

public class EmailCheckRequest {

    private String username;

    public EmailCheckRequest() {}

    public EmailCheckRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
