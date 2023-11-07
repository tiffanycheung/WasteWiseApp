package com.example.wastewise;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.wastewise.databinding.ActivityMainBinding;
//import com.example.wastewise.databinding.EventsBinding;
//import com.example.wastewise.databinding.HomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Random;


public class Home extends AppCompatActivity {

    private ImageView leaderboardBtn, rewardsBtn, eventsBtn, cardBtn, referBtn, checkupBtn, logoutImage;
    private TextView factTxt, referTxt, checkupTxt, nameTxt;
    private FirebaseAuth fAuth;

    private FirebaseFirestore fStore;

    private String userId;
    private FrameLayout logoutContainer;

    private Button yesBtn, noBtn;



    /*
    HomeBinding binding;
*/
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.home);

        //setContentView(R.layout.home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //home is selected by default
        bottomNavigationView.setSelectedItemId(R.id.home);

        // initialisation
        factTxt = findViewById(R.id.factTxt);
        referTxt = findViewById(R.id.referTxt);
        checkupTxt = findViewById(R.id.checkupTxt);
        nameTxt = findViewById(R.id.profileNameTxt);
        leaderboardBtn = findViewById(R.id.leaderboardBox);
        rewardsBtn = findViewById(R.id.rewardsBox);
        eventsBtn = findViewById(R.id.eventsBox);
        cardBtn = findViewById(R.id.cardBox);
        referBtn = findViewById(R.id.referBox);
        checkupBtn = findViewById(R.id.checkupBox);

        logoutImage = findViewById(R.id.logoutImage);
        logoutContainer = findViewById(R.id.logoutContainer);
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);

        //logout
        logoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutContainer.setVisibility(View.VISIBLE);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //log user out

                        //Back to main activity


                        Toast.makeText(getApplicationContext(), "User Logged Out", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Home.this, MainActivity.class);
                        startActivity(intent);
                        FirebaseAuth.getInstance().signOut();
            }
        });

        //Bottom Navigation Bar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                {
                    if (item.getItemId() == R.id.home) {
                    }

                    if (item.getItemId() == R.id.leaderboard) {
                        Intent intent = new Intent(Home.this, Leaderboard.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.forum) {
                        Intent intent = new Intent(Home.this, Forum.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.checkup) {
                        Intent intent = new Intent(Home.this, CheckupLanding.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.profile) {
                        Intent intent = new Intent(Home.this, Profile.class);
                        startActivity(intent);

                    }
                    return true;
                }
            }
        });
        }});

        // Welcome message to current user
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                        nameTxt.setText(documentSnapshot.get("fullName").toString());
                    }
                });


        // set up text prompts
        referTxt.setText("You could earn over 100 points. Eligibility and criteria and T&Cs apply.");
        checkupTxt.setText("Have you completed your daily waste checkup yet?");
        factTxt.setText(factOfTheDay());

        checkupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckupLanding.class);
                startActivity(intent);
                finish();
            }
        });
        referBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DigitalCard.class);
                intent.putExtra("PREVIOUS_PAGE", "Home");
                startActivity(intent);
                finish();
            }
        });
        leaderboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
                startActivity(intent);
                finish();
            }
        });

        rewardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Rewards.class);
                startActivity(intent);
                finish();
            }
        });

        eventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Events.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private String factOfTheDay() {
        // generate random number between 1 and 7 inclusive
        Random random = new Random();
        int randomNumber = random.nextInt(7) + 1;

        String fact = "";

        // choose a fact to display based on the random number generated
        switch (randomNumber) {
            case 1:
                fact = "Garden Organics Bin are collected fortnightly while General and Recycling are collected weekly.";
                break;
            case 2:
                fact = "It's important to rinse out food and beverage containers before recycling to prevent contamination of other recyclables.";
                break;
            case 3:
                fact = "Items like batteries, chemicals, and electronics should not be placed in regular recycling bins. Check for proper disposal locations in your area.";
                break;
            case 4:
                fact = "Keep plastic caps on bottles when recycling. They are usually made from a different type of plastic and can be recycled separately.";
                break;
            case 5:
                fact = "Look for recycling symbols on products to identify whether they are recyclable and in which bin they should be placed.";
                break;
            case 6:
                fact = "The most effective way to manage waste is to reduce consumption and reuse items whenever possible.";
                break;
            case 7:
                fact = "Plastic bags and other soft plastics should not be placed in recycling bins. Instead, they can be recycled at designated drop-off locations.";
                break;
        }

        return fact;
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.refer_friend);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        ImageView exitBtn = dialog.findViewById(R.id.exitBtn);
        TextView referTxt = dialog.findViewById(R.id.referTxt);
        TextView codeTxt = dialog.findViewById(R.id.codeTxt);
        ImageView copyBtn = dialog.findViewById(R.id.copyCodeBtn);
        Button sendBtn = dialog.findViewById(R.id.submitBtn);

        referTxt.setText("Tell your friends about us. You get 100 points from us when they create an account with us.");

        // generate a random code
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // create a StringBuilder to store the random code
        StringBuilder codeBuilder = new StringBuilder(6);
        // generate a 6-character random code
        for (int i = 0; i < 6; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        // convert the StringBuilder to a String
        String randomCode = codeBuilder.toString();
        codeTxt.setText(randomCode);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Email invite sent!", Toast.LENGTH_SHORT).show();
            }
        });

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Code copied to clipboard.", Toast.LENGTH_SHORT).show();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}

