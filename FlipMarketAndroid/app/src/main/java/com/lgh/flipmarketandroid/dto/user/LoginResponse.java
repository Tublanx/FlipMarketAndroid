package com.lgh.flipmarketandroid.dto.user;

import com.lgh.flipmarketandroid.dto.product.User;

public class LoginResponse {

    private String accessToken;

    private User user;

    public String getAccessToken() {
        return accessToken;
    }

    public User getUser() {
        return user;
    }
}
