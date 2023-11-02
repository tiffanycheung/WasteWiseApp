package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.profile);


        // TODO: put the below code in the onClick method for the digital card (to track where the back button in the digital card page should go)
        Intent intent = new Intent(Profile.this, DigitalCard.class);
        // to track previous page
        intent.putExtra("PREVIOUS_PAGE", "Profile");
        intent.putExtra("PAGE_USER_CAME_FROM", "Profile");
        startActivity(intent);
    }
}
