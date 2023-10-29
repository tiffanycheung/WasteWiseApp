package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wastewise.databinding.LeaderboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Leaderboard extends AppCompatActivity {

    //LeaderboardBinding binding;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.leaderboard);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.leaderboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                {
                    if (item.getItemId() == R.id.home) {
                        Intent intent = new Intent(Leaderboard.this, HomeActivity.class);
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
                }
            }
        });


        //binding for bottom navigation bar
       /* binding = LeaderboardBinding.inflate(getLayoutInflater());
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
*/
    }
}
