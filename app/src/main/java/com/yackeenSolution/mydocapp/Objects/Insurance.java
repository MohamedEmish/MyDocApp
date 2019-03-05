package com.yackeenSolution.mydocapp.Objects;

public class Insurance {
    int id;
    String name;
    String imageUri;

    public Insurance(String name, String imageUri) {
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
