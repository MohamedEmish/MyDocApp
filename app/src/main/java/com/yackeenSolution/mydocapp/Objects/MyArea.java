package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyArea implements Parcelable {

    public static final Parcelable.Creator<MyArea> CREATOR = new Parcelable.Creator<MyArea>() {
        @Override
        public MyArea createFromParcel(Parcel source) {
            return new MyArea(source);
        }

        @Override
        public MyArea[] newArray(int size) {
            return new MyArea[size];
        }
    };
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUri;
    @SerializedName("name")
    @Expose
    private String name;

    private MyArea(Parcel in) {
        this.id = in.readInt();
        this.imageUri = in.readString();
        this.name = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
