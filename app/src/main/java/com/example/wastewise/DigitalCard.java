package com.example.wastewise;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DigitalCard extends AppCompatActivity {

    private TextView nameTxt, congratsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.digital_card);

        // initialisations
        nameTxt = findViewById(R.id.nameTxt);
        congratsTxt = findViewById(R.id.congratsTxt);

        congratsTxt.setText("Congratulations for reaching your daily points.");
    }



}
