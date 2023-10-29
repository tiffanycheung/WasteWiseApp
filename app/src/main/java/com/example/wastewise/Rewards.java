package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wastewise.databinding.RewardsBinding;

public class Rewards extends AppCompatActivity {

    RewardsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.rewards);

        //binding for bottom navigation bar
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

    }



}
