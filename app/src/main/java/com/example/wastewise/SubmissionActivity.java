package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SubmissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.checkup_submission);

    }

}
