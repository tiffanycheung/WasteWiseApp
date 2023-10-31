package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckupLanding extends AppCompatActivity implements CheckupAdapter.onCheckupListener {

    private TextView descTxt;
    private RecyclerView checkupRecyclerView;
    public static ArrayList<Checkup> activitiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.checkups_landing);

        // initialisations
        checkupRecyclerView = findViewById(R.id.transactionRecycler);
        checkupRecyclerView.setHasFixedSize(true);
        descTxt = findViewById(R.id.descTxt);

        activitiesList = Checkup.createActivities();

        // set up prompt text
        descTxt.setText("Let’s track your waste management progress with these quick checkups!");

        setAdapter();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setAdapter();
    }

    private void setAdapter() {
        CheckupAdapter adapter = new CheckupAdapter(activitiesList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        checkupRecyclerView.setLayoutManager(layoutManager);
        checkupRecyclerView.setItemAnimator(new DefaultItemAnimator());
        checkupRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCheckupClick(int position) {
        Intent intent = new Intent(getApplicationContext(), CheckupActivity.class);
        intent.putExtra("ACTIVITY_NAME", activitiesList.get(position).getActivityName());
        startActivity(intent);
    }
}
