package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FamilyRelation implements Parcelable {
    public static final Parcelable.Creator<FamilyRelation> CREATOR = new Parcelable.Creator<FamilyRelation>() {
        @Override
        public FamilyRelation createFromParcel(Parcel source) {
            return new FamilyRelation(source);
        }

        @Override
        public FamilyRelation[] newArray(int size) {
            return new FamilyRelation[size];
        }
    };
    @SerializedName("name")
    @Expose
    private
    String name;
    @SerializedName("Id")
    @Expose
    int id;
    @SerializedName("ImageUrl")
    @Expose
    private
    String imageUrl;

    private FamilyRelation(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.imageUrl = in.readString();
    }

    public String getName() {
        return name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.imageUrl);
    }
}
