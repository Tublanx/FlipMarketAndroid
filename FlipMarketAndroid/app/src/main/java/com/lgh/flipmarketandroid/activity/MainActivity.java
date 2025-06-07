package com.lgh.flipmarketandroid.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgh.flipmarketandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myPageTextView = findViewById(R.id.btnMyPage);
        TextView cartTextView = findViewById(R.id.btnCart);
        TextView addProductTextView = findViewById(R.id.btnAddProduct);
        TextView loginTextView = findViewById(R.id.btnLogin);
        TextView signUpTextView = findViewById(R.id.btnSignup);

        SharedPreferences sharedPref = getSharedPreferences("auth", MODE_PRIVATE);
        String token = sharedPref.getString("jwt", null);

        boolean isLoggedIn = token != null;

        if (isLoggedIn) {
            loginTextView.setText("로그아웃");
            addProductTextView.setVisibility(View.VISIBLE);
            signUpTextView.setVisibility(View.GONE);
        } else {
            loginTextView.setText("로그인");
            addProductTextView.setVisibility(View.GONE);
            signUpTextView.setVisibility(View.VISIBLE);
        }

        loginTextView.setOnClickListener(e -> {
            Log.e("MainActivity", "로그인 or 로그아웃 버튼 클릭 중");
            
            if (loginTextView.getText().equals("로그인")) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                getSharedPreferences("auth", MODE_PRIVATE).edit().remove("jwt").apply();
                Intent intent = getIntent();
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}
