package com.example.wastewise;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Leaderboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.leaderboard);
    }

}
