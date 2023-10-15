package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.home);

        // get the intent that was sent from QuestionnaireActivity (after user signs up)
        Intent intent = getIntent();
        // Get the user's name and postcode from the intent
        String name = intent.getStringExtra("FULL_NAME");
        String postcode = intent.getStringExtra("POSTCODE");

        //TODO: need some way to figure out the user's local council based on their postcode input?
    }

}
