package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("Name")
    @Expose
    private
    String name;
    @SerializedName("Relationship")
    @Expose
    private
    String relationship;
    @SerializedName("RelationshipId")
    @Expose
    private
    int relationshipId;
    @SerializedName("DOB")
    @Expose
    private
    String birthDate;
    @SerializedName("Id")
    @Expose
    int id;
    @SerializedName("FamilyMemberId")
    @Expose
    private
    int familyMemberId;
    @SerializedName("ParentId")
    @Expose
    private
    int parentId;
    @SerializedName("PhoneNumber")
    @Expose
    private
    String phoneNumber;
    @SerializedName("Email")
    @Expose
    private
    String email;
    @SerializedName("Image")
    @Expose
    private
    String imageUrl;
    @SerializedName("Gender")
    @Expose
    private
    String gender;

    private FamilyMember(Parcel in) {
        this.name = in.readString();
        this.relationship = in.readString();
        this.relationshipId = in.readInt();
        this.birthDate = in.readString();
        this.id = in.readInt();
        this.familyMemberId = in.readInt();
        this.parentId = in.readInt();
        this.phoneNumber = in.readString();
        this.email = in.readString();
        this.imageUrl = in.readString();
        this.gender = in.readString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public int getRelationshipId() {
        return relationshipId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getFamilyMemberId() {
        return familyMemberId;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.relationship);
        dest.writeInt(this.relationshipId);
        dest.writeString(this.birthDate);
        dest.writeInt(this.id);
        dest.writeInt(this.familyMemberId);
        dest.writeInt(this.parentId);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.email);
        dest.writeString(this.imageUrl);
        dest.writeString(this.gender);
    }
}
