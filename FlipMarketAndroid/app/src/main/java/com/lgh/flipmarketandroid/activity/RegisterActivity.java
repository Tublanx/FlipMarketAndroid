package com.lgh.flipmarketandroid.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgh.flipmarketandroid.R;
import com.lgh.flipmarketandroid.config.ApiService;
import com.lgh.flipmarketandroid.config.RetrofitClient;
import com.lgh.flipmarketandroid.dto.user.EmailCheckRequest;
import com.lgh.flipmarketandroid.dto.user.EmailCheckResponse;
import com.lgh.flipmarketandroid.dto.user.RegisterRequest;
import com.lgh.flipmarketandroid.dto.user.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private String emailId;
    private String selectedEmail;
    private String pwd;
    private String name;
    private String age;
    private String phoneNum;
    private String fullEmailVal = "";
    private boolean isCheckEmail = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText emailEditText = findViewById(R.id.editEmailId);
        Spinner emailSpinner = findViewById(R.id.spinnerEmailDomain);
        Button checkEmailButton = findViewById(R.id.btn_check_email);
        EditText pwdEditText = findViewById(R.id.editPassword);
        EditText nameEditText = findViewById(R.id.editName);
        EditText ageEditText = findViewById(R.id.editAge);
        EditText phoneNumEditText = findViewById(R.id.editPhone);
        Button registerButton = findViewById(R.id.btnSignup);
        Button loginButton = findViewById(R.id.btnGoLogin);
        Button mainButton = findViewById(R.id.btnGoMain);

        // 이메일 선택박스 초기화
        String[] items = { "선택", "naver.com", "daum.net", "gmail.com", "nate.com", "기타(직접입력)" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emailSpinner.setAdapter(adapter);



        // "중복확인" 버튼 클릭 시 로직
        checkEmailButton.setOnClickListener(e1 -> {
            emailId = emailEditText.getText().toString().trim();
            selectedEmail = emailSpinner.getSelectedItem().toString();

            // 이메일아이디 입력란이 비어있을 경우
            if (emailId.isEmpty()) {
                Toast.makeText(getApplicationContext(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 이메일 선택 박스가 "선택"으로 선택되어있을 경우
            if (selectedEmail.equals("선택")) {
                Toast.makeText(getApplicationContext(), "이메일 양식을 선택해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 이메일 선택 박스에 "기타(직접입력)"이 선택되어있고, 이메일아이디의 형식이 이메일 정규식에 맞지 않을 경우
            if (selectedEmail.equals("기타(직접입력)") && !emailId.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // "기타(직접입력)" 이 선택되어있으면 이메일아이디 입력란으로 이메일 값 할당, 그게 아닐 경우 콤보박스 값까지 받아서 이메일 값 할당
            if (selectedEmail.equals("기타(직접입력)")) {
                fullEmailVal = emailId;
            } else {
                fullEmailVal = emailId + "@" + selectedEmail;
            }

            // 서버 통신 요청
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.219.106:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            EmailCheckRequest request = new EmailCheckRequest(fullEmailVal);
            ApiService apiService = retrofit.create(ApiService.class);

            // 이메일 중복 여부 판단
            apiService.checkEmail(request).enqueue(new Callback<EmailCheckResponse>() {
                @Override
                public void onResponse(@NonNull Call<EmailCheckResponse> call, @NonNull Response<EmailCheckResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body().isExists) {
                            Toast.makeText(getApplicationContext(), "이미 사용 중인 이메일입니다.", Toast.LENGTH_SHORT).show();
                            isCheckEmail = false;
                        } else {
                            Toast.makeText(getApplicationContext(), "사용 가능한 이메일입니다.", Toast.LENGTH_SHORT).show();
                            isCheckEmail = true;
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<EmailCheckResponse> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), "통신 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // "회원가입" 버튼 클릭 시 로직
        registerButton.setOnClickListener(e2 -> {
            emailId = emailEditText.getText().toString().trim();
            selectedEmail = emailSpinner.getSelectedItem().toString();
            pwd = pwdEditText.getText().toString().trim();
            name = nameEditText.getText().toString().trim();
            age = ageEditText.getText().toString().trim();
            phoneNum = phoneNumEditText.getText().toString().trim();

            if (!isCheckEmail) {
                Toast.makeText(getApplicationContext(), "이메일 중복 확인을 진행해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 비밀번호가 8자 미만일 경우
            if (pwd.length() < 8) {
                Toast.makeText(getApplicationContext(), "비밀번호는 8자 이상으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 이름 입력란이 비어있을 경우
            if (name.isEmpty()) {
                Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 나이 값이 1 아래일 경우
            if (age.isEmpty() || Integer.parseInt(age) < 1) {
                Toast.makeText(getApplicationContext(), "나이를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 전화번호가 11자가 아닐 경우 => 하이픈은 제외하고 숫자만 입력
            if (phoneNum.length() != 11) {
                Toast.makeText(getApplicationContext(), "전화번호는 하이픈 제외 11자로 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // "기타(직접입력)" 이 선택되어있으면 이메일아이디 입력란으로 이메일 값 할당, 그게 아닐 경우 콤보박스 값까지 받아서 이메일 값 할당
            if (selectedEmail.equals("기타(직접입력)")) {
                fullEmailVal = emailId;
            } else {
                fullEmailVal = emailId + "@" + selectedEmail;
            }

            // 서버 통신 요청
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.219.106:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // 회원가입 진행 시 요청보낼 request 정의
            RegisterRequest request = new RegisterRequest(fullEmailVal, pwd, name, Integer.parseInt(age), phoneNum, "USER");
            ApiService apiService = retrofit.create(ApiService.class);

            // 회원가입 진행
            apiService.register(request).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
                    } else {
                        Toast.makeText(getApplicationContext(), "회원가입 실패. 오류 발생", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                    Log.e("Retrofit", "Login Failed: " + t.getMessage(), t);
                    Toast.makeText(getApplicationContext(), "서버 통신 오류", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // "로그인 화면으로" 버튼 클릭 시 로그인 페이지로 이동 (애니메이션 구현)
        loginButton.setOnClickListener(e3 -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });

        // "메인으로 이동" 버튼 클릭 시 메인 페이지로 이동 (애니메이션 구현)
        mainButton.setOnClickListener(e4 -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });

    }

}
