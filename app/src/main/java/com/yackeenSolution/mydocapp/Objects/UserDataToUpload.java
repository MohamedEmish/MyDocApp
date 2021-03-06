package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataToUpload implements Parcelable {

    public static final Parcelable.Creator<UserDataToUpload> CREATOR = new Parcelable.Creator<UserDataToUpload>() {
        @Override
        public UserDataToUpload createFromParcel(Parcel source) {
            return new UserDataToUpload(source);
        }

        @Override
        public UserDataToUpload[] newArray(int size) {
            return new UserDataToUpload[size];
        }
    };
    @SerializedName("FBId")
    @Expose
    private
    String fbId;
    @SerializedName("Lang")
    @Expose
    private
    String lang;
    @SerializedName("AppointmentReminder")
    @Expose
    private
    Boolean appointmentReminder;
    @SerializedName("EnableNotification")
    @Expose
    private
    Boolean enableNotification;
    @SerializedName("InsuranceCompanyId")
    @Expose
    private
    Integer insuranceCompanyId;
    @SerializedName("InsuranceCompanyImageUrl")
    @Expose
    private
    String insuranceCompanyImageUrl;
    @SerializedName("Id")
    @Expose
    int id;
    @SerializedName("DoctorId")
    @Expose
    private
    Integer doctorId;
    @SerializedName("FirstName")
    @Expose
    private
    String firstName;
    @SerializedName("LastName")
    @Expose
    private
    String lastName;
    @SerializedName("DOB")
    @Expose
    private
    String dateOfBirth;
    @SerializedName("Gender")
    @Expose
    private
    Boolean gender;
    @SerializedName("MobileNumber")
    @Expose
    private
    String phoneNumber;
    @SerializedName("Email")
    @Expose
    private
    String email;
    @SerializedName("Password")
    @Expose
    private
    String password;
    @SerializedName("Address")
    @Expose
    private
    String address;
    @SerializedName("Image")
    @Expose
    private
    String imageUri;

    public UserDataToUpload() {
    }

    private UserDataToUpload(Parcel in) {
        this.fbId = in.readString();
        this.lang = in.readString();
        this.appointmentReminder = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.enableNotification = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.insuranceCompanyId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.insuranceCompanyImageUrl = in.readString();
        this.id = in.readInt();
        this.doctorId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.dateOfBirth = in.readString();
        this.gender = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.phoneNumber = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.address = in.readString();
        this.imageUri = in.readString();
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setAppointmentReminder(Boolean appointmentReminder) {
        this.appointmentReminder = appointmentReminder;
    }

    public void setEnableNotification(Boolean enableNotification) {
        this.enableNotification = enableNotification;
    }

    public void setInsuranceCompanyId(Integer insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public void setInsuranceCompanyImageUrl(String insuranceCompanyImageUrl) {
        this.insuranceCompanyImageUrl = insuranceCompanyImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
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

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
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
        dest.writeString(this.fbId);
        dest.writeString(this.lang);
        dest.writeValue(this.appointmentReminder);
        dest.writeValue(this.enableNotification);
        dest.writeValue(this.insuranceCompanyId);
        dest.writeString(this.insuranceCompanyImageUrl);
        dest.writeInt(this.id);
        dest.writeValue(this.doctorId);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.dateOfBirth);
        dest.writeValue(this.gender);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.address);
        dest.writeString(this.imageUri);
    }
}
