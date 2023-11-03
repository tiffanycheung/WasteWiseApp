package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Explanation extends AppCompatActivity {
    private TextView descTxt, dailyTxt, dailyPoints, option1, option2, option3, option4, option5, option6, points1, points2, points3, points4, points5, points6;
    private ImageView opt1, opt2, opt3, opt4, opt5, opt6, helpBtn, backBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.explanation);

        Intent intent = getIntent();
        String userCameFrom = intent.getStringExtra("PAGE_USER_CAME_FROM");

        // initialisations
        descTxt = findViewById(R.id.descTxt);
        dailyTxt = findViewById(R.id.dailyTxt);
        dailyPoints = findViewById(R.id.dailyPoints);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        option6 = findViewById(R.id.option6);
        points1 = findViewById(R.id.points1);
        points2 = findViewById(R.id.points2);
        points3 = findViewById(R.id.points3);
        points4 = findViewById(R.id.points4);
        points5 = findViewById(R.id.points5);
        points6 = findViewById(R.id.points6);
        opt1 = findViewById(R.id.yellowBox1);
        opt2 = findViewById(R.id.yellowBox2);
        opt3 = findViewById(R.id.yellowBox3);
        opt4 = findViewById(R.id.yellowBox4);
        opt5 = findViewById(R.id.yellowBox5);
        opt6 = findViewById(R.id.yellowBox6);
        helpBtn = findViewById(R.id.helpBtn);
        backBtn = findViewById(R.id.backBtn);

        // set up text prompts
        descTxt.setText("Curious how to earn more points? Get involved with these activities to start earning them now!");
        dailyTxt.setText("Use WasteWise daily");
        dailyPoints.setText("5 points each day");
        option1.setText("Bring your own cup to our participating cafes");
        option2.setText("Bring your reusable bags to our participating supermarkets");
        option3.setText("Complete checkup activities");
        option4.setText("Bring your own cutlery to our participating restaurants");
        option5.setText("Thrift three items of clothing");
        option6.setText("Participate in a waste management event");
        points1.setText("20 points");
        points2.setText("30 points");
        points3.setText("5 points for each activity");
        points4.setText("20 points");
        points5.setText("40 points");
        points6.setText("100 points");

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Explanation.this, DigitalCard.class);
                intent.putExtra("PREVIOUS_PAGE", "Explanation");
                startActivity(intent);
            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Explanation.this, DigitalCard.class);
                intent.putExtra("PREVIOUS_PAGE", "Explanation");
                startActivity(intent);
            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Explanation.this, CheckupLanding.class);
                startActivity(intent);
            }
        });

        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Explanation.this, DigitalCard.class);
                intent.putExtra("PREVIOUS_PAGE", "Explanation");
                startActivity(intent);
            }
        });

        opt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Explanation.this, DigitalCard.class);
                intent.putExtra("PREVIOUS_PAGE", "Explanation");
                startActivity(intent);
            }
        });

        opt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Explanation.this, Events.class);
                startActivity(intent);
            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Explanation.this, HelpPage.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Explanation.this, DigitalCard.class);
                intent.putExtra("PREVIOUS_PAGE", userCameFrom);
                startActivity(intent);
            }
        });

    }
}
