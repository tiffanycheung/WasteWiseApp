package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {

    private TextView profileNameTxt, profilePointTxt, exchangePointTxt;
    private Button editProfileBtn;
    private ImageView settingsTab, digitalCardTab, logoutTab;

    private FirebaseAuth fAuth;

    private FirebaseFirestore fStore;

    private String userId;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.profile);

        //initialise
        profileNameTxt = findViewById(R.id.profileNameTxt);
        profilePointTxt = findViewById(R.id.profilePointTxt);
        exchangePointTxt = findViewById(R.id.exchangePointTxt);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        settingsTab = findViewById(R.id.settingsTab);
        digitalCardTab = findViewById(R.id.digitalCardTab);
        logoutTab = findViewById(R.id.logoutTab);

        //Set Name of User, Points and Exchange Items
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                profileNameTxt.setText(documentSnapshot.get("fullName").toString());
                profilePointTxt.setText(documentSnapshot.get("pointsNo").toString() + " Points" );
                exchangePointTxt.setText(documentSnapshot.get("exchangeItemNo").toString() + " Exchange Items");


            }
        });


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                {
                    if (item.getItemId() == R.id.home) {
                        Intent intent = new Intent(Profile.this, Home.class);
                        startActivity(intent);
                    }

                    if (item.getItemId() == R.id.leaderboard) {

                    }
                    if (item.getItemId() == R.id.forum) {
                        Intent intent = new Intent(Profile.this, Forum.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.checkup) {
                        Intent intent = new Intent(Profile.this, CheckupLanding.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.profile) {

                    }
                    return true;
                }
            }
        });

        //Setting Tab
        settingsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Settings Page Similar logic to Digital Card

                Intent intent = new Intent(getApplicationContext(), HelpPage.class);
                startActivity(intent);
                finish();
            }
        });


        //Digital Card Tab

        digitalCardTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DigitalCard.class);
                intent.putExtra("PREVIOUS_PAGE", "Profile");
                intent.putExtra("PAGE_USER_CAME_FROM", "Profile");
                startActivity(intent);
                finish();
            }
        });


        //TODO: Make Logout Tab

    }
}
