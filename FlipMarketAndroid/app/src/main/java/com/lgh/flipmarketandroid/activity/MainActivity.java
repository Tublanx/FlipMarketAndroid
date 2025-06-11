package com.lgh.flipmarketandroid.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.flipmarketandroid.R;
import com.lgh.flipmarketandroid.adapter.ProductAdapter;
import com.lgh.flipmarketandroid.config.ApiService;
import com.lgh.flipmarketandroid.config.RetrofitClient;
import com.lgh.flipmarketandroid.dto.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myPageTextView = findViewById(R.id.btnMyPage);
        TextView cartTextView = findViewById(R.id.btnCart);
        TextView addProductTextView = findViewById(R.id.btnAddProduct);
        TextView loginTextView = findViewById(R.id.btnLogin);
        TextView signUpTextView = findViewById(R.id.btnSignup);

        // 로그인 정보 확인
        SharedPreferences sharedPref = getSharedPreferences("auth", MODE_PRIVATE);
        String token = sharedPref.getString("jwt", null);
        Long userNum = sharedPref.getLong("userNum", -1);

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

        // 상품 리스트 나열
        recyclerView = findViewById(R.id.recyclerViewProductList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = RetrofitClient.getApiService();

        apiService.getProducts(userNum).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();

                    productAdapter = new ProductAdapter(MainActivity.this, productList);
                    recyclerView.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "상품 리스트 조회 오류", Toast.LENGTH_SHORT).show();
            }
        });

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

        myPageTextView.setOnClickListener(e -> {
            if (isLoggedIn) {
                // 마이페이지로 이동
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                Toast.makeText(getApplicationContext(), "로그인 하신 후에 이용 가능한 서비스입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        cartTextView.setOnClickListener(e -> {
            if (isLoggedIn) {
                // 장바구니로 이동
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                Toast.makeText(getApplicationContext(), "로그인 하신 후에 이용 가능한 서비스입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 각 상품 클릭 시 상품 상세 조회 화면으로 이동
        recyclerView.setOnClickListener(e -> {

        });
    }
}
