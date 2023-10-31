package com.example.wastewise;

import java.util.ArrayList;

public class Checkup {
    private String activityName;
    private int image;
    private boolean completed;

    public Checkup(String activityName, int image, boolean completed) {
        this.activityName = activityName;
        this.image = image;
        this.completed = completed;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static ArrayList<Checkup> createActivities() {
        ArrayList<Checkup> activitiesList = new ArrayList<>();
        activitiesList.add(new Checkup("Checkup 1: Waste Disposal", R.drawable.checkup_1, false));
        activitiesList.add(new Checkup("Checkup 2: E-Waste", R.drawable.checkup_2, false));
        activitiesList.add(new Checkup("Checkup 3: Biomedical Waste", R.drawable.checkup_3, false));
        activitiesList.add(new Checkup("Checkup 4: Hazardous Waste", R.drawable.checkup_4, false));
        activitiesList.add(new Checkup("Checkup 5: Green Waste", R.drawable.checkup_5, false));
        return activitiesList;
    }

}
