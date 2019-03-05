package com.yackeenSolution.mydocapp.Objects;

public class Speciality {
    int id;
    String imageUri;
    String name;

    public Speciality(String name, String imageUri) {
        this.name = name;
        this.imageUri = imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImageUri() {
        return imageUri;
    }
}
