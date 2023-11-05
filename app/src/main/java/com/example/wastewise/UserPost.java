package com.example.wastewise;

public class UserPost {

    String name, email,title, description, userId, timeStamp;

    public UserPost() {

    }

    public UserPost(String name, String email, String title, String description,
                    String userId, String timeStamp) {
        this.name = name;
        this.email = email;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.timeStamp = timeStamp;
        //this.uDp = uDp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.title = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.title = userId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.title = timeStamp;
    }

 /*   public String getDp() {
        return uDp;
    }

    public void setDp(String uDp) {
        this.uDp = uDp;
    }*/
}


