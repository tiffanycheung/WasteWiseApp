package com.example.wastewise;

/**
 * To create user objects
 */

public class Users {

    private String fName;
    private String lName;
    private String dob;
    private String postcode;

    public Users() {

    }

    public Users(String fName, String lName, String dob, String postcode) {
        this.fName = fName;
        this.lName = lName;
        this.dob = dob;
        this.postcode = postcode;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getDob() {
        return dob;
    }

    public String getPostcode() {
        return postcode;
    }
}