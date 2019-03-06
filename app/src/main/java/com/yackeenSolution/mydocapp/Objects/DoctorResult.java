package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorResult implements Parcelable {

    public static final Parcelable.Creator<DoctorResult> CREATOR = new Parcelable.Creator<DoctorResult>() {
        @Override
        public DoctorResult createFromParcel(Parcel source) {
            return new DoctorResult(source);
        }

        @Override
        public DoctorResult[] newArray(int size) {
            return new DoctorResult[size];
        }
    };
    private int id;
    private String name;
    private String area;
    private String jobTitle;
    private String workPlace;
    private String imageUrl;

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

    private Boolean isFavorite;

    protected DoctorResult(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.area = in.readString();
        this.jobTitle = in.readString();
        this.workPlace = in.readString();
        this.imageUrl = in.readString();
        this.isFavorite = (Boolean) in.readValue(Boolean.class.getClassLoader());
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
        dest.writeString(this.jobTitle);
        dest.writeString(this.workPlace);
        dest.writeString(this.imageUrl);
        dest.writeValue(this.isFavorite);
    }
}
