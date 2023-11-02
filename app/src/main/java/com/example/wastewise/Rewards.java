package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.wastewise.databinding.RewardsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Rewards extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private FirebaseAuth fAuth;

    private FirebaseFirestore fStore;

    private String userId;

    private TextView pointsNoTxt, exchangeItemTxt, popUpTitle, popUpDescription, claimNowDescription, claimLinkDescription;

    private Button claimBtn1, claimBtn2, claimBtn3, claimBtn4, returnHomeBtn, yesBtn, noBtn, claimHomeBtn ;

    private FrameLayout frameLayout, claimNowContainer, claimLinkLayout;

    private ImageView popUpImage, claimNowImage, claimLinkImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.rewards);

        //initialisation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        pointsNoTxt = findViewById(R.id.pointsNoTxt);
        exchangeItemTxt = findViewById(R.id.exchangeItemTxt);
        claimBtn1 = findViewById(R.id.claimNowButton1);
        claimBtn2 = findViewById(R.id.claimNowButton2);
        claimBtn3 = findViewById(R.id.claimNowButton3);
        claimBtn4 = findViewById(R.id.claimNowButton4);

        frameLayout = findViewById(R.id.popupContainer);
        popUpTitle = findViewById(R.id.popUpTitle);
        popUpDescription = findViewById(R.id.popUpDescription);
        returnHomeBtn = findViewById(R.id.returnHomeBtn);
        popUpImage = findViewById(R.id.popUpImage);
        claimNowDescription = findViewById(R.id.claimNowDescription);
        claimNowImage = findViewById(R.id.claimNowImage);

        claimNowContainer = findViewById(R.id.claimNowContainer);
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);

        claimLinkLayout = findViewById(R.id.claimLinkLayout);
        claimLinkDescription = findViewById(R.id.claimLinkDescription);
        claimLinkImage = findViewById(R.id.claimLinkImage);
        claimHomeBtn = findViewById(R.id.claimHomeBtn);


        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                {
                    if (item.getItemId() == R.id.home) {
                        Intent intent = new Intent(Rewards.this, Home.class);
                        startActivity(intent);
                    }

                    if (item.getItemId() == R.id.leaderboard) {
                        Intent intent = new Intent(Rewards.this, Leaderboard.class);
                        startActivity(intent);

                    }
                    if (item.getItemId() == R.id.forum) {
                    }
                    if (item.getItemId() == R.id.checkup) {
                    }
                    if (item.getItemId() == R.id.profile) {
                    }
                    return true;
                }
            }
        });



        // Customise welcome message to current user
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                // Display points and exchange items
                pointsNoTxt.setText(documentSnapshot.get("pointsNo").toString() + " Points");
                exchangeItemTxt.setText(documentSnapshot.get("exchangeItemNo").toString() + " Exchange Items");

                // points no = documentSnapshot

                Object pointsNoObject = documentSnapshot.get("pointsNo");
              //  Integer userPoints = ((Long) pointsNoObject).intValue();

                Integer userPoints = 200;


                //REWARD 1 : if <100 points change the "Claim Now" button display to Unavailable for
                if (userPoints <100) {

                  showRewardUnavailable(claimBtn1, R.drawable.deals_coffee_icon);
                 /* showRewardUnavailable(claimBtn2, R.drawable.deals_grocery_icon);
                  showRewardUnavailable(claimBtn3, R.drawable.deals_food_icon);
                  showRewardUnavailable(claimBtn4, R.drawable.deals_petrol_icon);*/

                } else {
                    String coffeeDescription = "Would you like to claim 10% off your next Coffee order?";
                    String coffeeCodeText = " to redeem your coffee.";
                    String coffeeCode = "<html><font color='#2D8B00'><u><b>LK993</b></u></font></html>";
                    showRewardsAvailable(claimBtn1, coffeeDescription, coffeeCodeText, coffeeCode, R.drawable.deals_coffee_icon);

                    //TODO: CHANGE POINTS AFTER REDEMPTION

                }

                //REWARD 2: Grocery Reward

                if (userPoints <250) {
                    showRewardUnavailable(claimBtn2, R.drawable.deals_grocery_icon);
                } else {

                }



            }
        });



    }

    private void showRewardUnavailable(Button button, int imageResource) {
        button.setText("Unavailable");
        button.setTextSize(9);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.VISIBLE);

                // set text

                popUpTitle.setText("Oh No :( ");
                popUpDescription.setText("You do not have enough points to claim this. \nEarn more with our activities!" );
                popUpImage.setImageResource(imageResource);

                // Return Home Button
                returnHomeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        frameLayout.setVisibility(View.INVISIBLE);
                    }
                });



            }
        });

    }

    private void showRewardsAvailable(Button button, String description, String claimCodeDescription, String claimCode, int imageResource) {


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                claimNowContainer.setVisibility(View.VISIBLE);

                // set text

                button.setText("Claim Now");
                claimNowDescription.setText(description);
                claimNowImage.setImageResource(imageResource);

                // Yes Btn
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        claimLinkLayout.setVisibility(View.VISIBLE);
                        claimNowContainer.setVisibility(View.INVISIBLE);
                        claimLinkDescription.setText(Html.fromHtml("Present the code " + claimCode +claimCodeDescription));

                        claimLinkImage.setImageResource(imageResource);
                        claimHomeBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                claimLinkLayout.setVisibility(View.INVISIBLE);

                            }
                        });


                    }
                });

                // No Button

                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        claimNowContainer.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });

        //TODO: RANDOM CODE GENERATOR


    }



}
