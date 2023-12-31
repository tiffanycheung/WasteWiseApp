package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


// sign up page
public class SignUp extends AppCompatActivity {

    private EditText emailEditTxt, passwordEditTxt;
    private Button continueBtn;

    private FirebaseAuth mAuth;

    private ImageView backBtn;

    //Checks if user is already logged in, and if so, goes straight to Home
  /*  @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.sign_up);
        mAuth = FirebaseAuth.getInstance();

        // initialise widgets
        continueBtn = findViewById(R.id.continueBtn);
        emailEditTxt = findViewById(R.id.emailEditTxt);
        passwordEditTxt = findViewById(R.id.passwordEditTxt);
        backBtn = findViewById(R.id.likeBtn);


        // go to questionnaire page when button is clicked on
        continueBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                boolean canContinue = canContinue();

                String email, password;
                email = String.valueOf(emailEditTxt.getText());
                password = String.valueOf(passwordEditTxt.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUp.this, "Please enter email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidEmail(email)) {
                    Toast.makeText(SignUp.this, "Invalid email address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUp.this, "Please enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Please Enter your Details",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SignUp.this, UserQuestionnaire.class);
                                    intent.putExtra("EMAIL", email);
                                    intent.putExtra("PASSWORD", password);
                                    startActivity(intent);
                                    finish();

                                   /* // Sign in success, update UI with the signed-in user's information
                                    */
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

          /*      if (canContinue) {
                    Intent intent = new Intent(SignUpActivity.this, QuestionnaireActivity.class);
                    startActivity(intent);
                    finish();
                }*/

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    // check whether all fields have been filled out correctly before proceeding
  /*  private boolean canContinue() {
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
*/

    // check whether email used to sign up is valid
    private Boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}