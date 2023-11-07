package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Events extends AppCompatActivity {
    //EventsBinding binding;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.events);
        getSupportActionBar().hide();

        //Bottom Navigation Bar

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                { if (item.getItemId() == R.id.home) {
                    Intent intent = new Intent(Events.this, Home.class);
                    startActivity(intent);
                }

                    if (item.getItemId() == R.id.leaderboard) {
                        Intent intent = new Intent(Events.this, Leaderboard.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.forum) {
                        Intent intent = new Intent(Events.this, Forum.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.checkup) {
                        Intent intent = new Intent(Events.this, CheckupLanding.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.profile) {
                        Intent intent = new Intent(Events.this, Profile.class);
                        startActivity(intent);
                    }
                    return true;
                }
            }
        });



       /* binding = EventsBinding.inflate(getLayoutInflater());
        // hide action bar
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

       // setContentView(R.layout.home);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
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
}
