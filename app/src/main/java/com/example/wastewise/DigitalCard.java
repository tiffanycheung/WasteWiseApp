package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

//import com.example.wastewise.databinding.DigitalCardBinding;
//import com.example.wastewise.databinding.HomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DigitalCard extends AppCompatActivity {

    private TextView nameTxt, emptyStateTxt;
    private ImageView backBtn, infoBtn;
    private ConstraintLayout transaction1, transaction2, transaction3, transaction4;
    private String previousPage;

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
        emptyStateTxt = findViewById(R.id.emptyStateTxt);
        backBtn = findViewById(R.id.backBtn);
        infoBtn = findViewById(R.id.infoBtn);
        transaction1 = findViewById(R.id.business1);
        transaction2 = findViewById(R.id.business2);
        transaction3 = findViewById(R.id.business3);
        transaction4 = findViewById(R.id.business4);

        // record the page that the digital card was clicked on from
        Intent intent = getIntent();
        previousPage = intent.getStringExtra("PREVIOUS_PAGE");

        //Bottom Navigation Bar
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.home);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                { if (item.getItemId() == R.id.home) {
//                    Intent intent = new Intent(DigitalCard.this, Home.class);
//                    startActivity(intent);
//                }
//
//                    if (item.getItemId() == R.id.leaderboard) {
//                        Intent intent = new Intent(DigitalCard.this, Leaderboard.class);
//                        startActivity(intent);
//                    }
//                    if (item.getItemId() == R.id.forum) {
//                    }
//                    if (item.getItemId() == R.id.checkup) {
//                    }
//                    if (item.getItemId() == R.id.profile) {
//                    }
//                    return true;
//                }
//            }
//        });

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

        // TODO: get user's name, points and number of exchange items from database
        String name = "";

        // make transaction history visible only for Amanda Vuong, otherwise all new users will have empty transaction history
        if(name.equals("Amanda Vuong")) {
            transaction1.setVisibility(View.VISIBLE);
            transaction2.setVisibility(View.VISIBLE);
            transaction3.setVisibility(View.VISIBLE);
            transaction4.setVisibility(View.VISIBLE);
            emptyStateTxt.setVisibility(View.INVISIBLE);
        }

        // go back to the previous page
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (previousPage.equals("Home")) {
                    Intent intent = new Intent(DigitalCard.this, Home.class);
                    startActivity(intent);
                }

                if (previousPage.equals("Profile")) {
                    Intent intent = new Intent(DigitalCard.this, Profile.class);
                    startActivity(intent);
                }

                if (previousPage.equals("Explanation")) {
                    Intent intent = new Intent(DigitalCard.this, Explanation.class);
                    startActivity(intent);
                }

            }
        });

        // go to explanation page
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DigitalCard.this, Explanation.class);
                intent.putExtra("PAGE_USER_CAME_FROM", previousPage);
                startActivity(intent);
            }
        });
    }


}
