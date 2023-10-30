package com.example.wastewise;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReferFriendActivity extends AppCompatActivity {

    private TextView referTxt;
    private ImageView copyBtn, sendInviteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.refer_friend);

        // initialisations
        copyBtn = findViewById(R.id.copyCodeBtn);

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