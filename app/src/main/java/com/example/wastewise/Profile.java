package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {

    private TextView profileNameTxt, profilePointTxt, exchangePointTxt;
    private Button editProfileBtn, yesBtn, noBtn;
    private ImageView settingsTab, digitalCardTab, logoutTab;

    private FirebaseAuth fAuth;

    private FirebaseFirestore fStore;

    private String userId;

    private BottomNavigationView bottomNavigationView;

    private FrameLayout logoutContainer;



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
        logoutContainer = findViewById(R.id.logoutContainer);
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);

        //Set Name of User, Points and Exchange Items
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = fAuth.getCurrentUser();
        if (currentUser != null) {

        userId = currentUser.getUid();
        }

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null) {
                profileNameTxt.setText(documentSnapshot.get("fullName").toString());
                profilePointTxt.setText(documentSnapshot.get("pointsNo").toString() + " Points" );
                exchangePointTxt.setText(documentSnapshot.get("exchangeItemNo").toString() + " Exchange Items");


            } else {
                    profileNameTxt.setText("Username");
                    profilePointTxt.setText("0 Points" );
                    exchangePointTxt.setText("0 Exchange Items");

                }
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

        logoutTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutContainer.setVisibility(View.VISIBLE);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //log user out

                        //Back to main activity


                        Toast.makeText(getApplicationContext(), "User Logged Out", Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent(Profile.this, MainActivity.class);
                        startActivity(intent);
                        FirebaseAuth.getInstance().signOut();
                       // finish();

                       /* fAuth = FirebaseAuth.getInstance();
                        fAuth.signOut();
*/

                    }
                });

                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        logoutContainer.setVisibility(View.INVISIBLE);

                    }
                });

            }
        });


        //TODO: Make Logout Tab

    }
}
