package com.yackeenSolution.mydocapp.Objects;

public class DoctorResult {

    int id;
    String name;
    String area;
    String jobTitle;
    String workPlace;
    String imageUrl;
    Boolean isFavorite;

    public DoctorResult(String name, String area, String jobTitle, String workPlace, String imageUrl, boolean isFavorite) {
        this.name = name;
        this.area = area;
        this.jobTitle = jobTitle;
        this.workPlace = workPlace;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
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

    public String getArea() {
        return area;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
