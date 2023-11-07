package com.example.wastewise;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.wastewise.databinding.LeaderboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Leaderboard extends AppCompatActivity {

    //LeaderboardBinding binding;
    private TextView userPoints, georgeTxt, georgePts, fionaTxt, fionaPts, fourthPlace, fifthPlace, sixthPlace, johnTxt, johnPts, willisTxt, willisPts, kenTxt, kenPts, emptyState;
    private ImageView newUserIcon, amandaIcon, georgeIcon, fionaIcon, johnIcon, willisIcon, kenIcon, addBtn;

    BottomNavigationView bottomNavigationView;

    private FirebaseAuth fAuth;

    private FirebaseFirestore fStore;

    private String userId, name, points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.leaderboard);

        // initialisations
        newUserIcon = findViewById(R.id.newUserIcon);
        userPoints = findViewById(R.id.userPoints);
        amandaIcon = findViewById(R.id.amandaIcon);
        georgeIcon = findViewById(R.id.georgeIcon);
        georgeTxt = findViewById(R.id.georgeTxt);
        georgePts = findViewById(R.id.georgePoints);
        fionaIcon = findViewById(R.id.fionaIcon);
        fionaTxt = findViewById(R.id.fionaTxt);
        fionaPts = findViewById(R.id.fionaPoints);
        fourthPlace = findViewById(R.id.fourthPlace);
        johnTxt = findViewById(R.id.johnTxt);
        johnIcon = findViewById(R.id.johnImg);
        johnPts = findViewById(R.id.johnPoints);
        fifthPlace = findViewById(R.id.fifthPlace);
        willisIcon = findViewById(R.id.willisImg);
        willisTxt = findViewById(R.id.willisTxt);
        willisPts = findViewById(R.id.willisPoints);
        sixthPlace = findViewById(R.id.sixthPlace);
        kenIcon = findViewById(R.id.kenImg);
        kenTxt = findViewById(R.id.kenTxt);
        kenPts = findViewById(R.id.kenPoints);
        emptyState = findViewById(R.id.emptyStateTxt);
        addBtn = findViewById(R.id.addBtn);

        // set prompt text for new users with no friends
        emptyState.setText("Add your friends to see them on the leaderboard!");

        //get user's name and points from database
       // String name = "";

        //String name;
      // String points = "";

        //points = "";


       fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

               points = documentSnapshot.get("pointsNo").toString() + "pts" ;

               name =  documentSnapshot.get("fullName").toString() +"";

                userPoints.setText(points);
                // if the user is Amanda Vuong (user with pre-filled details in the database), show her leaderboard
                if (name.equals("Amanda Vuong")) {
                    showLeaderboard();
                }

          /*  if (documentSnapshot.contains("fullName")) {
                    name = documentSnapshot.getString("fullName");
                } else {
                name = "";
            }*/

            }
        });

       // userPoints.setText(points);
/*
        // if the user is Amanda Vuong (user with pre-filled details in the database), show her leaderboard
        if (name.equals("Amanda Vuong")) {
            showLeaderboard();
        }*/

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        //Bottom Navigation View

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.leaderboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                {
                    if (item.getItemId() == R.id.home) {
                        Intent intent = new Intent(Leaderboard.this, Home.class);
                        startActivity(intent);
                    }

                    if (item.getItemId() == R.id.leaderboard) {

                    }
                    if (item.getItemId() == R.id.forum) {
                        Intent intent = new Intent(Leaderboard.this, Forum.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.checkup) {
                        Intent intent = new Intent(Leaderboard.this, CheckupLanding.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.profile) {
                        Intent intent = new Intent(Leaderboard.this, Profile.class);
                        startActivity(intent);
                    }
                    return true;
                }
            }
        });


        //binding for bottom navigation bar
       /* binding = LeaderboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set default selected item to "Leaderboard"
        binding.bottomNavigationView.setSelectedItemId(R.id.leaderboard);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.leaderboard) {
            }
            if (item.getItemId() == R.id.forum) {
            }
            if (item.getItemId() == R.id.checkup) {
            }
            if (item.getItemId() == R.id.profile) {
            }
            return true;
        });
    }
*/
    }

    private void showLeaderboard() {
        // make new user icon and empty state invisible
        newUserIcon.setVisibility(View.INVISIBLE);
        emptyState.setVisibility(View.INVISIBLE);
        // make leaderboard elements visible
        amandaIcon.setVisibility(View.VISIBLE);
        georgeIcon.setVisibility(View.VISIBLE);
        georgeTxt.setVisibility(View.VISIBLE);
        georgePts.setVisibility(View.VISIBLE);
        fionaIcon.setVisibility(View.VISIBLE);
        fionaTxt.setVisibility(View.VISIBLE);
        fionaPts.setVisibility(View.VISIBLE);
        fourthPlace.setVisibility(View.VISIBLE);
        johnTxt.setVisibility(View.VISIBLE);
        johnIcon.setVisibility(View.VISIBLE);
        johnPts.setVisibility(View.VISIBLE);
        fifthPlace.setVisibility(View.VISIBLE);
        willisIcon.setVisibility(View.VISIBLE);
        willisTxt.setVisibility(View.VISIBLE);
        willisPts.setVisibility(View.VISIBLE);
        sixthPlace.setVisibility(View.VISIBLE);
        kenIcon.setVisibility(View.VISIBLE);
        kenTxt.setVisibility(View.VISIBLE);
        kenPts.setVisibility(View.VISIBLE);
        //get Amanda's points from database and show
        String amandaPts = points;
        userPoints.setText(amandaPts);
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.refer_friend);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        ImageView exitBtn = dialog.findViewById(R.id.exitBtn);
        TextView referTxt = dialog.findViewById(R.id.referTxt);
        TextView codeTxt = dialog.findViewById(R.id.codeTxt);
        ImageView copyBtn = dialog.findViewById(R.id.copyCodeBtn);
        Button sendBtn = dialog.findViewById(R.id.submitBtn);

        referTxt.setText("Tell your friends about us. You get 100 points from us when they create an account with us.");

        // generate a random code
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // create a StringBuilder to store the random code
        StringBuilder codeBuilder = new StringBuilder(6);
        // generate a 6-character random code
        for (int i = 0; i < 6; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        // convert the StringBuilder to a String
        String randomCode = codeBuilder.toString();
        codeTxt.setText(randomCode);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Leaderboard.this, "Email invite sent!", Toast.LENGTH_SHORT).show();
            }
        });

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Leaderboard.this, "Code copied to clipboard.", Toast.LENGTH_SHORT).show();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
