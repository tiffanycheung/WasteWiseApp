package com.example.wastewise;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CheckupActivity extends AppCompatActivity {

    private ArrayList<String> questionsList;
    private TextView titleTxt, questionTxt1, questionTxt2, questionTxt3;
    private Button submitBtn, yesBtn, noBtn, yesBtn3, noBtn3;
    private ImageView backBtn;
    private EditText answerEditTxt;
    private boolean yesBtnClicked = false;
    private boolean noBtnClicked = false;
    private boolean yesBtn3Clicked = false;
    private boolean noBtn3Clicked = false;
    public static String completedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.checkup_activity);

        // get the name of the activity from intent
        Intent intent = getIntent();
        String activityName = intent.getStringExtra("ACTIVITY_NAME");


        // create list of questions based on activity name
        questionsList = createQuestions(activityName);

        // initialisations
        titleTxt = findViewById(R.id.titleTxt);
        questionTxt1 = findViewById(R.id.questionTxt1);
        questionTxt2 = findViewById(R.id.questionTxt2);
        questionTxt3 = findViewById(R.id.questionTxt3);
        submitBtn = findViewById(R.id.submitBtn);
        backBtn = findViewById(R.id.likeBtn);
        answerEditTxt = findViewById(R.id.answerEditTxt);
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        yesBtn3 = findViewById(R.id.yesBtn3);
        noBtn3 = findViewById(R.id.noBtn3);

        titleTxt.setText(activityName);

        // set the text of the questions
        questionTxt1.setText(questionsList.get(0));
        questionTxt2.setText(questionsList.get(1));
        questionTxt3.setText(questionsList.get(2));

        // check the position of the checkup activity in activitiesList
        int position = 0;
        for (int i = 0; i < CheckupLanding.activitiesList.size(); i++) {
            if (CheckupLanding.activitiesList.get(i).getActivityName().equals(activityName)) {
                position = i;
            }
        }

        // make the question boxes green for the even checkup activities
        if (position == 1 || position == 3) {
            questionTxt1.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_box));
            questionTxt2.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_box));
            questionTxt3.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_box));
        }

        // make sure that only one option can be selected at a time for q1 and q3

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesBtnClicked == false && noBtnClicked == false) {
                    yesBtn.setBackgroundColor(Color.parseColor("#D6D6D6"));
                }

                if (noBtnClicked == true) {
                    yesBtn.setBackgroundColor(Color.parseColor("#D6D6D6"));
                    noBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    noBtnClicked = false;
                }
                yesBtnClicked = true;
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noBtnClicked == false && yesBtnClicked == false) {
                    noBtn.setBackgroundColor(Color.parseColor("#D6D6D6"));
                }

                if (yesBtnClicked == true) {
                    noBtn.setBackgroundColor(Color.parseColor("#D6D6D6"));
                    yesBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    yesBtnClicked = false;
                }
                noBtnClicked = true;
            }
        });

        yesBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesBtn3Clicked == false && noBtn3Clicked == false) {
                    yesBtn3.setBackgroundColor(Color.parseColor("#D6D6D6"));
                }

                if (noBtn3Clicked == true) {
                    yesBtn3.setBackgroundColor(Color.parseColor("#D6D6D6"));
                    noBtn3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    noBtn3Clicked = false;
                }
                yesBtn3Clicked = true;
            }
        });

        noBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noBtn3Clicked == false && yesBtn3Clicked == false) {
                    noBtn3.setBackgroundColor(Color.parseColor("#D6D6D6"));
                }

                if (yesBtn3Clicked == true) {
                    noBtn3.setBackgroundColor(Color.parseColor("#D6D6D6"));
                    yesBtn3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    yesBtn3Clicked = false;
                }
                noBtn3Clicked = true;
            }
        });


        // submit button goes to another page + updates completion
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check completion of activity before submission
                boolean allFilledOut = filledOut(yesBtnClicked, noBtnClicked, yesBtn3Clicked, noBtn3Clicked);

                // go to submission page only if all fields are filled out
                if (allFilledOut == true) {

                    // take the name of the completed activity to update completion status later
                    completedActivity = activityName;

                    // go back to checkups landing page
                    Intent intent = new Intent(CheckupActivity.this, CheckupSubmission.class);
                    startActivity(intent);
                }

                // TODO: add 5 points to the user in Firebase database

            }
        });

        // exit button goes back to landing page
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckupActivity.this, CheckupLanding.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<String> createQuestions(String activityName) {

        ArrayList<String> questionsList = new ArrayList<String>();

        switch(activityName) {
            case "Checkup 1: Waste Disposal":
                questionsList.add("Did you separate your recyclables from your regular trash?");
                questionsList.add("Please provide examples of how you've personally contributed to reducing waste or promoting recycling in your local community.");
                questionsList.add("Did you compost organic materials like food scraps?");
                break;
            case "Checkup 2: E-Waste":
                questionsList.add("Did you avoid disposing of electronic waste in regular household bins?");
                questionsList.add("Have you ever recycled electronic devices? If so, where did you take them for recycling?");
                questionsList.add("Have you participated in an e-waste program?");
                break;
            case "Checkup 3: Biomedical Waste":
                questionsList.add("Do you have a designated container or area in your home for the safe storage of biomedical waste materials?");
                questionsList.add("What practices or precautions do you take when disposing of biomedical waste from your household, including items like used syringes, expired medications, or other medical supplies?");
                questionsList.add("Are you aware of any local guidelines or regulations for the disposal of household biomedical waste in your area?");
                break;
            case "Checkup 4: Hazardous Waste":
                questionsList.add("Did you properly label and store your household chemicals in a safe way?");
                questionsList.add("Can you describe any practices or precautions you take when disposing of hazardous household materials?");
                questionsList.add("Did you refrain from pouring chemicals down the drains?");
                break;
            case "Checkup 5: Green Waste":
                questionsList.add("Did you avoid mixing green waste with regular household waste?");
                questionsList.add("What methods or practices do you currently use to manage and dispose of green waste from your garden or yard?");
                questionsList.add("Do you compost leaves and grass clippings to make nutrient-rich soil?");
                break;
        }

        return questionsList;
    }

    private boolean filledOut(boolean yesBtnClicked, boolean noBtnClicked, boolean yesBtn3Clicked, boolean noBtn3Clicked) {
        boolean editTxtFilled = true;
        boolean yesNoFilled = true;
        boolean allFilledOut = false;


        if(TextUtils.isEmpty(answerEditTxt.getText().toString())) {
            answerEditTxt.setError("Please enter your response.");
            editTxtFilled = false;
        }

        if(!((yesBtnClicked || noBtnClicked) && (yesBtn3Clicked || noBtn3Clicked))) {
            yesNoFilled = false;
            Toast.makeText(CheckupActivity.this, "Please answer all questions.", Toast.LENGTH_SHORT).show();
        }

        if(editTxtFilled && yesNoFilled) {
            allFilledOut = true;
        }

        if(!((yesBtnClicked || noBtnClicked) && (yesBtn3Clicked || noBtn3Clicked) && editTxtFilled)) {
            Toast.makeText(CheckupActivity.this, "Please answer all questions.", Toast.LENGTH_SHORT).show();
        }

        return allFilledOut;
    }


}
