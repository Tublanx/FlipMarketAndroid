package com.lgh.flipmarketandroid.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgh.flipmarketandroid.R;
import com.lgh.flipmarketandroid.config.ApiService;
import com.lgh.flipmarketandroid.config.RetrofitClient;
import com.lgh.flipmarketandroid.dto.product.User;
import com.lgh.flipmarketandroid.dto.user.LoginRequest;
import com.lgh.flipmarketandroid.dto.user.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        TextView registerText = findViewById(R.id.signUpTextView);

        loginButton.setOnClickListener(e -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }

            LoginRequest request = new LoginRequest(username, password);
            ApiService apiService = RetrofitClient.getInstance(this).create(ApiService.class);

            apiService.login(request).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        LoginResponse loginResponse = response.body();

                        String token = loginResponse.getAccessToken();
                        User user = loginResponse.getUser();
                        Long userNum = user.getNum();

                        // save
                        SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("jwt", token);
                        editor.putLong("userNum", userNum);
                        editor.apply();

                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    Log.e("Retrofit", "Login Failed: " + t.getMessage(), t);
                    Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_SHORT).show();
                }
            });
        });

        registerText.setOnClickListener(e -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });
    }
}
