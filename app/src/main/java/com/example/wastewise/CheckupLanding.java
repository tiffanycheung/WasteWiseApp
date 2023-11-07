package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CheckupLanding extends AppCompatActivity implements CheckupAdapter.onCheckupListener {

    private TextView descTxt;
    private RecyclerView checkupRecyclerView;
    private CheckupAdapter adapter;
    public static ArrayList<Checkup> activitiesList = Checkup.createActivities();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.checkups_landing);

        // initialisations
        checkupRecyclerView = findViewById(R.id.transactionRecycler);
        descTxt = findViewById(R.id.descTxt);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.checkup);


        // set up prompt text
        descTxt.setText("Let’s track your waste management progress with these quick checkups!");

        // set up recycler view
        checkupRecyclerView.setHasFixedSize(true);
        adapter = new CheckupAdapter(activitiesList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        checkupRecyclerView.setLayoutManager(layoutManager);
        checkupRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // add bottom navigation bar

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                { if (item.getItemId() == R.id.home) {
                    Intent intent = new Intent(CheckupLanding.this, Home.class);
                    startActivity(intent);
                }

                    if (item.getItemId() == R.id.leaderboard) {
                        Intent intent = new Intent(CheckupLanding.this, Leaderboard.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.forum) {
                        Intent intent = new Intent(CheckupLanding.this, Forum.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.checkup) {

                    }
                    if (item.getItemId() == R.id.profile) {
                        Intent intent = new Intent(CheckupLanding.this, Profile.class);
                        startActivity(intent);

                    }
                    return true;
                }
            }
        });


    }


    // update recyclerview for completed activities
    @Override
    protected void onStart() {
        super.onStart();

        // update the completion status of activities that have been submitted
        for (int i = 0; i < CheckupLanding.activitiesList.size(); i++) {
            if (CheckupLanding.activitiesList.get(i).getActivityName().equals(CheckupActivity.completedActivity)) {
                CheckupLanding.activitiesList.get(i).setCompleted(true);
            }
        }
        adapter.notifyDataSetChanged();
    }

    // go to activities page only if the activity hasn't been completed previously
    @Override
    public void onCheckupClick(int position) {

        if (activitiesList.get(position).getCompleted()) {
            Toast.makeText(CheckupLanding.this, "You have already completed this activity.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), CheckupActivity.class);
            intent.putExtra("ACTIVITY_NAME", activitiesList.get(position).getActivityName());
            startActivity(intent);
        }

    }
}
