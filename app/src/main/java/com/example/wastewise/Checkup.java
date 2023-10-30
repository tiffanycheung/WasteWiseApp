package com.example.wastewise;

import java.util.ArrayList;

public class Checkup {
    private String activityName;
    private String numOfQuestions;
    private int image;
    private boolean completed;

    public Checkup(String activityName, String numOfQuestions, int image, boolean completed) {
        this.activityName = activityName;
        this.numOfQuestions = numOfQuestions;
        this.image = image;
        this.completed = completed;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(String numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static ArrayList<Checkup> createActivities() {
        ArrayList<Checkup> activitiesList = new ArrayList<>();
        activitiesList.add(new Checkup("Checkup 1: Waste Disposal", "3 Questions", R.drawable.checkup_1, true));
        activitiesList.add(new Checkup("Checkup 2: E-Waste", "3 Questions", R.drawable.checkup_2, false));
        activitiesList.add(new Checkup("Checkup 3: Biomedical Waste", "3 Questions", R.drawable.checkup_3, false));
        activitiesList.add(new Checkup("Checkup 4: Hazardous Waste", "3 Questions", R.drawable.checkup_4, true));
        activitiesList.add(new Checkup("Checkup 5: Green Waste", "3 Questions", R.drawable.checkup_5, true));
        return activitiesList;
    }

}
