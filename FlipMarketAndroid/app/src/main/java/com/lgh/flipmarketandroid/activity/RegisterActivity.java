package com.lgh.flipmarketandroid.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgh.flipmarketandroid.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText emailEditText = findViewById(R.id.editTextEmail);
        EditText pwdEditText = findViewById(R.id.editTextPassword);
        EditText nameEditText = findViewById(R.id.editTextName);
        EditText ageEditText = findViewById(R.id.editTextAge);
        EditText phoneNumEditText = findViewById(R.id.editTextPhone);
        Button registerButton = findViewById(R.id.buttonRegister);
        Button loginButton = findViewById(R.id.buttonGoToLogin);
        Button mainButton = findViewById(R.id.buttonGoToMain);

        registerButton.setOnClickListener(e -> {

        });

        loginButton.setOnClickListener(e -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });

        mainButton.setOnClickListener(e -> {

        });

    }
}
