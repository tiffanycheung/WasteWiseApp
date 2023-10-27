package com.example.wastewise;

/**
 * To create user objects
 */

public class Users {

    private String email;
    private String fullName;
    private String dob;
    private String postcode;

    public Users() {

    }

    public Users(String email, String fullName, String dob, String postcode) {
        this.email = email;
        this.fullName = fullName;
        this.dob = dob;
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public String getfullName() {
        return fullName;
    }

    public String getDob() {
        return dob;
    }

    public String getPostcode() {
        return postcode;
    }
}