package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditTxt, passwordEditTxt;
    private TextView forgotPasswordTxt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.login);

        // initialise widgets
        loginBtn = findViewById(R.id.loginBtn);
        emailEditTxt = findViewById(R.id.emailEditTxt);
        passwordEditTxt = findViewById(R.id.passwordEditTxt);
        forgotPasswordTxt = findViewById(R.id.forgotPasswordTxt);

        // go to home page when button is clicked on
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: check user exists in firebase database, customise home page based on user

            }
        });

        // TODO: forgot password functionality (but maybe we can put this on low priority)
        forgotPasswordTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void login() {

    }


}
