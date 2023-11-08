package com.example.wastewise;

public class ForumComment {
    String id, comment, timestamp, name,email, uDp;

    public ForumComment() {

    }

    public ForumComment(String comment, String timestamp, String name, String uDp) {
        //this.id = id;
        this.comment = comment;
        this.timestamp = timestamp;
        this.name = name;
       // this.email = email;
       this.uDp = uDp;

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getuDp() {
        return uDp;
    }

    public void setuDp(String uDp) {
        this.uDp = uDp;
    }
}
