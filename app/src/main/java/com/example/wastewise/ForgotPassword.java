package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity  {

    private EditText emailEditTxt;
    private Button sendEmailBtn;
    private FirebaseAuth mAuth;

    private ImageView backBtn;

    private String userEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.forgot_password);

        //initialisation
        emailEditTxt = findViewById(R.id.emailEditTxt);
        sendEmailBtn = findViewById(R.id.sendEmailBtn);
        backBtn = findViewById(R.id.backBtn);
        mAuth = FirebaseAuth.getInstance();

        //Reset Button Listener
        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = emailEditTxt.getText().toString().trim();
                if (!TextUtils.isEmpty(userEmail)) {
                    ResetPassword();
                } else {
                    emailEditTxt.setError("Please enter email.");
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void ResetPassword() {
        mAuth.sendPasswordResetEmail(userEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ForgotPassword.this, "Reset password link has been sent to your email.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPassword.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPassword.this, "Password reset unsuccessful.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
