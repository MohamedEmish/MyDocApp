package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Insurance implements Parcelable {
    public static final Parcelable.Creator<Insurance> CREATOR = new Parcelable.Creator<Insurance>() {
        @Override
        public Insurance createFromParcel(Parcel source) {
            return new Insurance(source);
        }

        @Override
        public Insurance[] newArray(int size) {
            return new Insurance[size];
        }
    };

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUri;
    @SerializedName("Name")
    @Expose
    private String name;

    public Insurance() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUri(String imageUri) {
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


    protected Insurance(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.imageUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageUri);
    }
}
