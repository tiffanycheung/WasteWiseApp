package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


// user questionnaire page
public class UserQuestionnaire extends AppCompatActivity {

    private EditText fnameEditTxt, lnameEditTxt, dobEditTxt, postcodeEditTxt;
    private Button startBtn;
    private ImageView backBtn;
    String email;
    String password;

    private FirebaseFirestore fStore;

    private FirebaseAuth fAuth;

    private String userID;

    private Integer pointsNo, exchangeItemNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.user_questionnaire);

        // initialise widgets
        fnameEditTxt = findViewById(R.id.fnameEditTxt);
        lnameEditTxt = findViewById(R.id.lnameEditTxt);
        dobEditTxt = findViewById(R.id.dobEditTxt);
        postcodeEditTxt = findViewById(R.id.postcodeEditTxt);
        startBtn = findViewById(R.id.startBtn);
        backBtn = findViewById(R.id.likeBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if all fields are filled out
                boolean canSignUp = checkSignUp();
                if (canSignUp) {
                    // create user object and retrieve name and postcode
                    insertUserData();
                    // go to home page and pass the user's name and postcode
                    Intent intent = new Intent(UserQuestionnaire.this, Home.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserQuestionnaire.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });



    }

    // To create user objects
    private void insertUserData() {

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");
        password = intent.getStringExtra("PASSWORD");

        pointsNo = 0;
        exchangeItemNo = 0;


        String fullName = fnameEditTxt.getText().toString() + " " + lnameEditTxt.getText().toString();
        String dob = dobEditTxt.getText().toString();
        String postcode = postcodeEditTxt.getText().toString();

        Users user = new Users(email, fullName, dob, postcode);

        //Create entry in Firebase realtime database

                        userID = fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("users").document(userID);
                        Map<String,Object> userProfile = new HashMap<>();
                        userProfile.put("email", email);
                        userProfile.put("fullName", fullName);
                        userProfile.put("dob", dob);
                        userProfile.put("postcode", postcode);
                        userProfile.put("pointsNo", pointsNo);
                        userProfile.put("exchangeItemNo", exchangeItemNo);

                        documentReference.set(userProfile).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UserQuestionnaire.this, "Account Created.",
                                        Toast.LENGTH_SHORT).show();
                                Log.d("TAG", "onSuccess: user Profile is created");

                            }

                        });

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
