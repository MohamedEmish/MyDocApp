package com.yackeenSolution.mydocapp.Objects;

public class FamilyMember {

    String firstName;
    String lastName;
    String date;
    int gender;
    String mobile;
    String relation;
    String imageUri;

    int id;

    public FamilyMember(String firstName, String lastName, String date, int gender, String mobile, String relation, String imageUri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.gender = gender;
        this.mobile = mobile;
        this.relation = relation;
        this.imageUri = imageUri;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }

    public int getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRelation() {
        return relation;
    }

    public String getImageUri() {
        return imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
