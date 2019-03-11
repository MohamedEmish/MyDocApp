package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {
    public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String mobileNumber;
    private String gender;
    private String password;
    private String imageUri;

    // TODO: Remove this Constructor after clear Dummy Data and ((API)) connection
    public UserData(String firstName, String lastName, String email, String birthDate, String mobileNumber, String gender, String password, String imageUri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.password = password;
        this.imageUri = imageUri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserData() {
    }

    protected UserData(Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.birthDate = in.readString();
        this.mobileNumber = in.readString();
        this.gender = in.readString();
        this.password = in.readString();
        this.imageUri = in.readString();
    }

    public int getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
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
        dest.writeString(this.email);
        dest.writeString(this.birthDate);
        dest.writeString(this.mobileNumber);
        dest.writeString(this.gender);
        dest.writeString(this.password);
        dest.writeString(this.imageUri);
    }
}
