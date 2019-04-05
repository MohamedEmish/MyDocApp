package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @Expose(serialize = false, deserialize = false)
    boolean selected = true;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUri;
    @SerializedName("name")
    @Expose
    private String name;

    public Speciality() {
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setName(String name) {
        this.name = name;
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
