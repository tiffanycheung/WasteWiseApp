package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReferFriend extends AppCompatActivity {

    private TextView referTxt;
    private ImageView copyBtn, sendInviteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.refer_friend);

        // initialisations
        referTxt = findViewById(R.id.referTxt);
        copyBtn = findViewById(R.id.copyCodeBtn);

        // set up text prompt
        referTxt.setText("Tell your friends about us. You get 100 points from us when they create an account with us.");

        // TODO: generate random text for the code

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        sendInviteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
//                startActivity(intent);
//                finish();
                // TODO: add toast saying "invite sent!"
            }
        });




    }

}