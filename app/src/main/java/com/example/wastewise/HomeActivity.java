package com.example.wastewise;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.wastewise.databinding.ActivityMainBinding;
//import com.example.wastewise.databinding.EventsBinding;
//import com.example.wastewise.databinding.HomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;


public class HomeActivity extends AppCompatActivity {

    private ImageView leaderboardBtn, rewardsBtn, eventsBtn, cardBtn, referBtn, checkupBtn;
    private TextView factTxt, referTxt, checkupTxt;
    private FirebaseAuth mAuth;


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
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Bottom Navigation Bar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                { if (item.getItemId() == R.id.home) {
                }

                    if (item.getItemId() == R.id.leaderboard) {
                        Intent intent = new Intent(HomeActivity.this, Leaderboard.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.forum) {
                    }
                    if (item.getItemId() == R.id.checkup) {
                    }
                    if (item.getItemId() == R.id.profile) {
                    }
                    return true;
                }
            }
        });
        //binding for navigation bar

     /*   binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView); // Replace with your actual ID
        Menu menu = bottomNavigationView.getMenu();

     //Adjust icon size for leaderboard
      MenuItem leaderboardItem = menu.findItem(R.id.leaderboard);
        Drawable leaderboardIcon = leaderboardItem.getIcon();
        if (leaderboardIcon != null) {
            int leaderboardIconWidth = getResources().getDimensionPixelSize(R.dimen.leaderboard_icon_width);
            int leaderboardIconHeight = getResources().getDimensionPixelSize(R.dimen.leaderboard_icon_height);
            leaderboardIcon.setBounds(0, 0, leaderboardIconWidth, leaderboardIconHeight);
            leaderboardItem.setIcon(leaderboardIcon);
        }
*/

        // TODO: customise welcome message to current user
        // FirebaseUser user = mAuth.getCurrentUser();

        // TODO: need some way to figure out the user's local council based on their postcode input? - last priority



        // initialisation
        factTxt = findViewById(R.id.factTxt);
        referTxt = findViewById(R.id.referTxt);
        checkupTxt = findViewById(R.id.checkupTxt);
        leaderboardBtn = findViewById(R.id.leaderboardBox);
        rewardsBtn = findViewById(R.id.rewardsBox);
        eventsBtn = findViewById(R.id.eventsBox);
        cardBtn = findViewById(R.id.cardBox);
        referBtn = findViewById(R.id.referBox);
        checkupBtn = findViewById(R.id.checkupBox);

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
                Intent intent = new Intent(getApplicationContext(), DigitalCardActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //bottom navigation navigation bar
     /*   binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
            }

            if (item.getItemId() == R.id.leaderboard) {
                Intent intent = new Intent(this, Leaderboard.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.forum) {
            }
            if (item.getItemId() == R.id.checkup) {
            }
            if (item.getItemId() == R.id.profile) {
            }
            return true;
        });*/

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
                Toast.makeText(HomeActivity.this, "Email invite sent!", Toast.LENGTH_SHORT).show();
            }
        });

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Code copied to clipboard.", Toast.LENGTH_SHORT).show();
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

