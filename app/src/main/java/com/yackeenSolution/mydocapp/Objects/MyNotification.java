package com.yackeenSolution.mydocapp.Objects;

public class MyNotification {

    int id;
    String title;
    String description;

    public MyNotification(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
