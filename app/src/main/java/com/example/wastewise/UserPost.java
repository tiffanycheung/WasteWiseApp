package com.example.wastewise;

public class UserPost {

   Number likesNo;
    String name, email,title, description, userId, timeStamp, documentId;

    public UserPost() {

    }

    public UserPost(String documentId, String name, String email, String title, String description,
                    String userId, String timeStamp, Number likesNo) {
        this.documentId = documentId;
        this.name = name;
        this.email = email;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.likesNo = likesNo;
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

   public Number getLikesNo() {
        return likesNo;
    }

    public void setLikesNo(Number likesNo) {
        this.likesNo = likesNo;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    /*   public String getDp() {
        return uDp;
    }

    public void setDp(String uDp) {
        this.uDp = uDp;
    }*/
}


