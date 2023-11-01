package com.example.wastewise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Explanation extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.explanation);

    }
}
