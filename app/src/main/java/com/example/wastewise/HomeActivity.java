package com.example.wastewise;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wastewise.databinding.ActivityMainBinding;
import com.example.wastewise.databinding.EventsBinding;
import com.example.wastewise.databinding.HomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;


public class HomeActivity extends AppCompatActivity {

    private ImageView leaderboardBtn, rewardsBtn, eventsBtn, cardBtn, referBtn;
    private TextView factTxt, referTxt, checkupTxt;
    private FirebaseAuth mAuth;


    HomeBinding binding;

    BottomNavigationView bottomNavigationView;
    Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.home);

        //binding for navigation bar

        binding = HomeBinding.inflate(getLayoutInflater());
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

        // set up text prompts
        referTxt.setText("You could earn over 100 points. Eligibility and criteria and T&Cs apply.");
        checkupTxt.setText("Have you completed your daily waste checkup yet?");
        factTxt.setText(factOfTheDay());

        referBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReferFriend.class);
                startActivity(intent);
                finish();
            }
        });
        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DigitalCard.class);
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
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
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
        });

    }

    private String factOfTheDay() {
        // generate random number between 1 and 5
        Random random = new Random();
        int min = 1;
        int max = 7;
        int randomNum = random.nextInt(max - min + 1) + min;

        String fact = "";

        // choose a fact to display based on the random number generated
        switch (randomNum) {
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
}
