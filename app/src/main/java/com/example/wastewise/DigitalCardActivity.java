package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.wastewise.databinding.DigitalCardBinding;
//import com.example.wastewise.databinding.HomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DigitalCardActivity extends AppCompatActivity {

    private TextView nameTxt;
    private ImageView backBtn;

    //DigitalCardBinding binding;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.digital_card);

        // initialisations
        nameTxt = findViewById(R.id.nameTxt);
        backBtn = findViewById(R.id.backBtn);

        //Bottom Navigation Bar

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                { if (item.getItemId() == R.id.home) {
                    Intent intent = new Intent(DigitalCardActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

                    if (item.getItemId() == R.id.leaderboard) {
                        Intent intent = new Intent(DigitalCardActivity.this, Leaderboard.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.forum) {
                    }
                    if (item.getItemId() == R.id.checkup) {
                    }
                    if (item.getItemId() == R.id.profile) {
                    }
                    return true;
                }
            }
        });


        /*binding = DigitalCardBinding.inflate(getLayoutInflater());

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

        });*/

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: needs to work out whether to go back to home page or profile page
                }
            });

            // TODO: recyclerview for transaction history (connected to database)

    }


}
