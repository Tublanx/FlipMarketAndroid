package com.lgh.flipmarketandroid.config;

import com.lgh.flipmarketandroid.dto.user.LoginRequest;
import com.lgh.flipmarketandroid.dto.user.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/android/login")
    Call<LoginResponse> login(@Body LoginRequest request);

}
