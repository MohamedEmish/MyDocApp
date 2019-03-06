package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class FacilityResult implements Parcelable {

    public static final Parcelable.Creator<FacilityResult> CREATOR = new Parcelable.Creator<FacilityResult>() {
        @Override
        public FacilityResult createFromParcel(Parcel source) {
            return new FacilityResult(source);
        }

        @Override
        public FacilityResult[] newArray(int size) {
            return new FacilityResult[size];
        }
    };
    private int id;
    private String name;
    private String area;
    private int phone;
    private String locationUri;
    private String imageUri;
    private String webUri;

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

    private boolean isFavorite;

    protected FacilityResult(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.area = in.readString();
        this.phone = in.readInt();
        this.locationUri = in.readString();
        this.imageUri = in.readString();
        this.webUri = in.readString();
        this.isFavorite = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.area);
        dest.writeInt(this.phone);
        dest.writeString(this.locationUri);
        dest.writeString(this.imageUri);
        dest.writeString(this.webUri);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }
}
