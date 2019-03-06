package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Speciality implements Parcelable {
    public static final Parcelable.Creator<Speciality> CREATOR = new Parcelable.Creator<Speciality>() {
        @Override
        public Speciality createFromParcel(Parcel source) {
            return new Speciality(source);
        }

        @Override
        public Speciality[] newArray(int size) {
            return new Speciality[size];
        }
    };
    private int id;
    private String imageUri;

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

    private String name;

    protected Speciality(Parcel in) {
        this.id = in.readInt();
        this.imageUri = in.readString();
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imageUri);
        dest.writeString(this.name);
    }
}
