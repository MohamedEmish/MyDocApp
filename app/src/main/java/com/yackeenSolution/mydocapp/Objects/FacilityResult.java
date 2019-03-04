package com.yackeenSolution.mydocapp.Objects;

public class FacilityResult {

    int id;
    String name;
    String area;
    int phone;
    String locationUri;
    String imageUri;
    String webUri;
    boolean isFavorite;

    public FacilityResult(String name, String area, int phone, String locationUri, String imageUri, String webUri, boolean isFavorite) {
        this.name = name;
        this.area = area;
        this.phone = phone;
        this.locationUri = locationUri;
        this.imageUri = imageUri;
        this.webUri = webUri;
        this.isFavorite = isFavorite;
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

    public int getPhone() {
        return phone;
    }

    public String getLocationUri() {
        return locationUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getWebUri() {
        return webUri;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
