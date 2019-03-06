package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class FamilyMember implements Parcelable {

    public static final Parcelable.Creator<FamilyMember> CREATOR = new Parcelable.Creator<FamilyMember>() {
        @Override
        public FamilyMember createFromParcel(Parcel source) {
            return new FamilyMember(source);
        }

        @Override
        public FamilyMember[] newArray(int size) {
            return new FamilyMember[size];
        }
    };
    private int id;
    private String firstName;
    private String lastName;
    private String date;
    private int gender;
    private String mobile;
    private String relation;


    public FamilyMember(String firstName, String lastName, String date, int gender, String mobile, String relation, String imageUri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.gender = gender;
        this.mobile = mobile;
        this.relation = relation;
        this.imageUri = imageUri;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }

    public int getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRelation() {
        return relation;
    }

    public String getImageUri() {
        return imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String imageUri;

    protected FamilyMember(Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.date = in.readString();
        this.gender = in.readInt();
        this.mobile = in.readString();
        this.relation = in.readString();
        this.imageUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.date);
        dest.writeInt(this.gender);
        dest.writeString(this.mobile);
        dest.writeString(this.relation);
        dest.writeString(this.imageUri);
    }
}
