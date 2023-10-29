package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wastewise.databinding.DigitalCardBinding;
import com.example.wastewise.databinding.HomeBinding;

public class DigitalCard extends AppCompatActivity {

    private TextView nameTxt, congratsTxt;
    private ImageView backBtn;

    DigitalCardBinding binding;

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

        binding = DigitalCardBinding.inflate(getLayoutInflater());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            if (item.getItemId() == R.id.home) {
                 intent = new Intent(this, HomeActivity.class);
            }

            if (item.getItemId() == R.id.leaderboard) {
                 intent = new Intent(this, Leaderboard.class);
            }
            if (item.getItemId() == R.id.forum) {
            }
            if (item.getItemId() == R.id.checkup) {
            }
            if (item.getItemId() == R.id.profile) {
            }
            if (intent != null) {
                startActivity(intent);
                finish();
                return true;
            }

            return false;
        });
    }



}
