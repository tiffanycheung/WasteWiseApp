package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wastewise.databinding.LeaderboardBinding;

public class Leaderboard extends AppCompatActivity {

    LeaderboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.leaderboard);

        //binding for bottom navigation bar
        binding = LeaderboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set default selected item to "Leaderboard"
        binding.bottomNavigationView.setSelectedItemId(R.id.leaderboard);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.leaderboard) {
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
