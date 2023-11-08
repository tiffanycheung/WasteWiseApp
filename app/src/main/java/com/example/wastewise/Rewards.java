package com.example.wastewise;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.Random;

public class Rewards extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private FirebaseAuth fAuth;

    private FirebaseFirestore fStore;

    private String userId;

    private TextView pointsNoTxt, exchangeItemTxt, popUpTitle, popUpDescription, claimNowDescription, claimLinkDescription;

    private Button claimBtn1, claimBtn2, claimBtn3, claimBtn4, returnHomeBtn, yesBtn, noBtn, claimHomeBtn ;

    private FrameLayout frameLayout, claimNowContainer, claimLinkLayout;

    private ImageView popUpImage, claimNowImage, claimLinkImage;

    private Integer userPoints;

    private DocumentReference documentReference;


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
        claimBtn1 = findViewById(R.id.editProfileBtn);
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

                    }

                    if (item.getItemId() == R.id.leaderboard) {
                        Intent intent = new Intent(Rewards.this, Leaderboard.class);
                        startActivity(intent);

                    }
                    if (item.getItemId() == R.id.forum) {
                        Intent intent = new Intent(Rewards.this, Forum.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.checkup) {
                        Intent intent = new Intent(Rewards.this, CheckupLanding.class);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.profile) {
                        Intent intent = new Intent(Rewards.this, Profile.class);
                        startActivity(intent);
                    }
                    return true;
                }
            }
        });



        // Customise welcome message to current user
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null) {

                    // Display points and exchange items
                    pointsNoTxt.setText(documentSnapshot.get("pointsNo").toString() + " Points");
                    exchangeItemTxt.setText(documentSnapshot.get("exchangeItemNo").toString() + " Exchange Items");

                    // Get User Points
                    Object pointsNoObject = documentSnapshot.get("pointsNo");
                    userPoints = ((Long) pointsNoObject).intValue();


                    //REWARD 1 : if <100 points change the "Claim Now" button display to Unavailable for
                    if (userPoints < 80) {

                        showRewardUnavailable(claimBtn1, R.drawable.deals_coffee_icon);

                    } else {
                        String coffeeDescription = "Would you like to claim 10% off your next Coffee order?";
                        String coffeeCodeText = " to redeem your coffee.";
                        String coffeeCode = "<html><font color='#2D8B00'><u><b>" + generateRandomCode() + "</b></u></font></html>";
                        showRewardsAvailable(claimBtn1, coffeeDescription, coffeeCodeText, coffeeCode, R.drawable.deals_coffee_icon, 100);


                    }

                    //REWARD 2: Grocery Reward

                    if (userPoints < 100) {
                        showRewardUnavailable(claimBtn2, R.drawable.deals_grocery_icon);
                    } else {
                        String groceryDescription = "Would you like to claim $5 off your Groceries?";
                        String groceryCodeText = " to get $5 off your groceries.";
                        String groceryCode = "<html><font color='#2D8B00'><u><b>" + generateRandomCode() + "</b></u></font></html>";
                        showRewardsAvailable(claimBtn2, groceryDescription, groceryCodeText, groceryCode, R.drawable.deals_grocery_icon, 250);
                    }

                    // REWARD 3: Free Bagel with Purchase

                    if (userPoints < 250) {
                        showRewardUnavailable(claimBtn3, R.drawable.deals_food_icon);
                    } else {
                        String foodDescription = "Would you like to claim a Free Bagel with Purchase?";
                        String foodCodeText = " to get a Free Bagel with Purchase.";
                        String foodCode = "<html><font color='#2D8B00'><u><b>" + generateRandomCode() + "</b></u></font></html>";
                        showRewardsAvailable(claimBtn3, foodDescription, foodCodeText, foodCode, R.drawable.deals_food_icon, 80);
                    }

                    // REWARD 4 : $10 off Petrol

                    if (userPoints < 450) {
                        showRewardUnavailable(claimBtn4, R.drawable.deals_petrol_icon);
                    } else {
                        String petrolDescription = "Would you like to claim $10 off Petrol?";
                        String petrolText = " to get $10 off Petrol.";
                        String petrolCode = "<html><font color='#2D8B00'><u><b>" + generateRandomCode() + "</b></u></font></html>";
                        showRewardsAvailable(claimBtn4, petrolDescription, petrolText, petrolCode, R.drawable.deals_petrol_icon, 450);

                    }
                }
            }
        });



    }

    private void showRewardUnavailable(Button button, int imageResource) {
        button.setText("Unavailable");
        button.setTextSize(8);


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

    private void showRewardsAvailable(Button button, String description, String claimCodeDescription, String claimCode, int imageResource, int points) {


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

                        userPoints = userPoints - points;
                        documentReference.update("pointsNo", userPoints);
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

    }

    //Generate a random code to claim rewards
    public static String generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(5);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            code.append(randomChar);
        }

        return code.toString();
    }







}
