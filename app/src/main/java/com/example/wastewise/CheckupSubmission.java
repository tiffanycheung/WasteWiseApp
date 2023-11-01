package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class CheckupSubmission extends AppCompatActivity {

    private Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.checkup_submission);

        // initialisations
        continueBtn = findViewById(R.id.continueBtn);

        // go back to checkups landing page
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckupSubmission.this, CheckupLanding.class);
                startActivity(intent);
            }
        });

    }

}
