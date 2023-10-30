package com.example.wastewise;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckupsActivity extends AppCompatActivity {

    private TextView descTxt;
    private RecyclerView checkupRecyclerView;
    private ArrayList<Checkup> activitiesList = Checkup.createActivities();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.checkups_main);

        // initialisations
        checkupRecyclerView = findViewById(R.id.checkupRecyclerView);
        descTxt = findViewById(R.id.descTxt);

        // set up prompt text
        descTxt.setText("Let’s track your waste management progress with these quick checkups!");

        setAdapter();

    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(activitiesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        checkupRecyclerView.setLayoutManager(layoutManager);
        checkupRecyclerView.setItemAnimator(new DefaultItemAnimator());
        checkupRecyclerView.setAdapter(adapter);
    }
}
