package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


// user questionnaire page
public class QuestionnaireActivity extends AppCompatActivity {

    private EditText fnameEditTxt, lnameEditTxt, dobEditTxt, postcodeEditTxt;
    private Button startBtn;
    private ImageView backBtn;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.user_questionnaire);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");

        // initialise widgets
        fnameEditTxt = findViewById(R.id.fnameEditTxt);
        lnameEditTxt = findViewById(R.id.lnameEditTxt);
        dobEditTxt = findViewById(R.id.dobEditTxt);
        postcodeEditTxt = findViewById(R.id.postcodeEditTxt);
        startBtn = findViewById(R.id.startBtn);
        backBtn = findViewById(R.id.backBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if all fields are filled out
                boolean canSignUp = checkSignUp();
                if (canSignUp) {
                    // create user object and retrieve name and postcode
                    insertUserData();
                    // go to home page and pass the user's name and postcode
                    Intent intent = new Intent(QuestionnaireActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionnaireActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // TODO: add a field for refer a friend code

    }

    // To create user objects
    private void insertUserData() {
        String fullName = fnameEditTxt.getText().toString() + " " + lnameEditTxt.getText().toString();
        String dob = dobEditTxt.getText().toString();
        String postcode = postcodeEditTxt.getText().toString();

        Users user = new Users(email, fullName, dob, postcode);

        // TODO: create entry in Firebase realtime database

    }

    // To check whether all fields have been filled out before proceeding
    private boolean checkSignUp() {
        String fName = fnameEditTxt.getText().toString();
        String lName = lnameEditTxt.getText().toString();
        String dob = dobEditTxt.getText().toString();
        String postcode = postcodeEditTxt.getText().toString();
        boolean everythingFilled = true;

        if(TextUtils.isEmpty(fName)) {
            fnameEditTxt.setError("Enter your first name");
            everythingFilled = false;
        }
        if (TextUtils.isEmpty(lName)) {
            lnameEditTxt.setError("Enter your last name");
            everythingFilled = false;
        }
        if (TextUtils.isEmpty(dob)) {
            dobEditTxt.setError("Enter your date of birth");
            everythingFilled = false;
        }
        if (postcode.length() != 4) {
            postcodeEditTxt.setError("Invalid postcode");
            everythingFilled = false;
        }
        if (TextUtils.isEmpty(postcode)) {
            postcodeEditTxt.setError("Enter your postcode");
            everythingFilled = false;
        }

        return everythingFilled;
    }

}
