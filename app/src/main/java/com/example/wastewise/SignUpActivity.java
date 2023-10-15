package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

// sign up page
public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditTxt, passwordEditTxt;
    private Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.sign_up);

        // initialise widgets
        continueBtn = findViewById(R.id.continueBtn);
        emailEditTxt = findViewById(R.id.emailEditTxt);
        passwordEditTxt = findViewById(R.id.passwordEditTxt);

        // go to questionnaire page when button is clicked on
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean canContinue = canContinue();
                if (canContinue) {
                    Intent intent = new Intent(SignUpActivity.this, QuestionnaireActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //TODO: do we need to store user input from this page (i.e. email and password)??

    }

    // check whether all fields have been filled out correctly before proceeding
    private boolean canContinue() {
        String email = emailEditTxt.getText().toString();
        String password = passwordEditTxt.getText().toString();
        boolean canContinue = true;

        // Check that the user has filled in all fields
        if (TextUtils.isEmpty(email)) {
            emailEditTxt.setError("Enter your email address");
            canContinue = false;
        }
        if (!isValidEmail(email)) {
            emailEditTxt.setError("Invalid email address");
            canContinue = false;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditTxt.setError("Enter a password");
            canContinue = false;
        }

        return canContinue;
    }

    // check whether email used to sign up is valid
    private Boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}