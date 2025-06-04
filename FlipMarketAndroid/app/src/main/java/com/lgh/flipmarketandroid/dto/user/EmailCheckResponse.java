package com.lgh.flipmarketandroid.dto.user;

import com.google.gson.annotations.SerializedName;

public class EmailCheckResponse {

    public boolean exists;

    public EmailCheckResponse() {
    }

    public boolean isExists() {
        return exists;
    }
    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
