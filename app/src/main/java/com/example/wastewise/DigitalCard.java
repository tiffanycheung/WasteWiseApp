package com.example.wastewise;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DigitalCard extends AppCompatActivity {

    private TextView nameTxt, congratsTxt;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.digital_card);

        // initialisations
        nameTxt = findViewById(R.id.nameTxt);
        congratsTxt = findViewById(R.id.congratsTxt);
        backBtn = findViewById(R.id.backBtn);

        congratsTxt.setText("Congratulations for reaching your daily points.");
    }



}
