package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FamilyMemberToUpload implements Parcelable {
    public static final Parcelable.Creator<FamilyMemberToUpload> CREATOR = new Parcelable.Creator<FamilyMemberToUpload>() {
        @Override
        public FamilyMemberToUpload createFromParcel(Parcel source) {
            return new FamilyMemberToUpload(source);
        }

        @Override
        public FamilyMemberToUpload[] newArray(int size) {
            return new FamilyMemberToUpload[size];
        }
    };
    @SerializedName("FamilyMemberId")
    @Expose
    Integer familyMemberId;
    @SerializedName("UserId")
    @Expose
    int userId;
    @SerializedName("Firstname")
    @Expose
    String firstName;
    @SerializedName("Lastname")
    @Expose
    String lastName;
    @SerializedName("DOB")
    @Expose
    String date;
    @SerializedName("Gender")
    @Expose
    Boolean gender;
    @SerializedName("Phonenumber")
    @Expose
    String phoneNumber;
    @SerializedName("RelationShipId")
    @Expose
    int relationId;
    @SerializedName("Email")
    @Expose
    String email;
    @SerializedName("ImageUrl")
    @Expose
    String imageUrl;

    public FamilyMemberToUpload() {
    }

    protected FamilyMemberToUpload(Parcel in) {
        this.familyMemberId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.date = in.readString();
        this.gender = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.phoneNumber = in.readString();
        this.relationId = in.readInt();
        this.email = in.readString();
        this.imageUrl = in.readString();
    }

    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.familyMemberId);
        dest.writeInt(this.userId);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.date);
        dest.writeValue(this.gender);
        dest.writeString(this.phoneNumber);
        dest.writeInt(this.relationId);
        dest.writeString(this.email);
        dest.writeString(this.imageUrl);
    }
}