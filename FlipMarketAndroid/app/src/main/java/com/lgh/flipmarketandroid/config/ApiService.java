package com.lgh.flipmarketandroid.config;

import com.lgh.flipmarketandroid.dto.product.Product;
import com.lgh.flipmarketandroid.dto.user.EmailCheckRequest;
import com.lgh.flipmarketandroid.dto.user.EmailCheckResponse;
import com.lgh.flipmarketandroid.dto.user.LoginRequest;
import com.lgh.flipmarketandroid.dto.user.LoginResponse;
import com.lgh.flipmarketandroid.dto.user.RegisterRequest;
import com.lgh.flipmarketandroid.dto.user.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/api/android/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/api/android/check-email")
    Call<EmailCheckResponse> checkEmail(@Body EmailCheckRequest request);

    @POST("/api/android/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @GET("/api/android/main")
    Call<List<Product>> getProducts(@Query("userNum") Long userNum);

}
