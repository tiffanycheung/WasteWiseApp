package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpPage extends AppCompatActivity {

    private TextView desc1, desc2, optionTxt, pointsTxt;
    private ImageView backBtn;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.help_page);

        // initialisations
        desc1 = findViewById(R.id.desc1);
        desc2 = findViewById(R.id.desc2);
        backBtn = findViewById(R.id.backBtn);
        optionTxt = findViewById(R.id.option1);
        pointsTxt = findViewById(R.id.points1);

        // set up descriptions
        String txt1 = "It’s great to see you are interested in earning points and contributing to our efforts to increase waste awareness. \n" +
                "\n" +
                "Let’s talk about how this will work. \n" +
                "\n" +
                "On the Redeem Points screen, you will see different activities you can participate in to gain points such as this:";
        String txt2 = "Click into the desired activity and it will take you to the Digital Card page. \n" +
                "\n" +
                "Present the card to one of our participating retailers to gain those points! \n" +
                "\n" +
                "Happy earning!";

        desc1.setText(txt1);
        desc2.setText(txt2);

        optionTxt.setText("Bring your own cup to our participating cafes");
        pointsTxt.setText("20 points");

        // go back to redeem points page
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpPage.this, Explanation.class);
                startActivity(intent);
            }
        });
    }
}
