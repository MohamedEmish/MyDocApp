package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("FBId")
    @Expose
    String facebookId;
    @SerializedName("Lang")
    @Expose
    String language;
    @SerializedName("AppointmentReminder")
    @Expose
    String appointmentReminder;
    @SerializedName("EnableNotification")
    @Expose
    String enableNotification;
    @SerializedName("InsuranceCompanyId")
    @Expose
    int insuranceId;
    @SerializedName("InsuranceCompanyImageUrl")
    @Expose
    String insuranceImage;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("DOB")
    @Expose
    private String birthDate;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Image")
    @Expose
    private String imageUri;

    public UserData() {
    }

    protected UserData(Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.birthDate = in.readString();
        this.gender = in.readString();
        this.mobileNumber = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.address = in.readString();
        this.facebookId = in.readString();
        this.imageUri = in.readString();
        this.language = in.readString();
        this.appointmentReminder = in.readString();
        this.enableNotification = in.readString();
        this.insuranceId = in.readInt();
        this.insuranceImage = in.readString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getPassword() {
        return password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getAppointmentReminder() {
        return appointmentReminder;
    }

    public void setAppointmentReminder(String appointmentReminder) {
        this.appointmentReminder = appointmentReminder;
    }

    public String getEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(String enableNotification) {
        this.enableNotification = enableNotification;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceImage() {
        return insuranceImage;
    }

    public void setInsuranceImage(String insuranceImage) {
        this.insuranceImage = insuranceImage;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.birthDate);
        dest.writeString(this.gender);
        dest.writeString(this.mobileNumber);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.address);
        dest.writeString(this.facebookId);
        dest.writeString(this.imageUri);
        dest.writeString(this.language);
        dest.writeString(this.appointmentReminder);
        dest.writeString(this.enableNotification);
        dest.writeInt(this.insuranceId);
        dest.writeString(this.insuranceImage);
    }
}
