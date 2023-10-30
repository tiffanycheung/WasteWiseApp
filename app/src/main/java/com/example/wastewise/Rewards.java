package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.wastewise.databinding.RewardsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Rewards extends AppCompatActivity {

   // RewardsBinding binding;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.rewards);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                {
                    if (item.getItemId() == R.id.home) {
                        Intent intent = new Intent(Rewards.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    if (item.getItemId() == R.id.leaderboard) {
                        Intent intent = new Intent(Rewards.this, Leaderboard.class);
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


        //binding for bottom navigation bar
/*
        binding = RewardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.leaderboard) {
                Intent intent = new Intent(this, DigitalCard.class);
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
*/

    }



}
