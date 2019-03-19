package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoctorResult implements Parcelable {
    @SerializedName("Id")
    @Expose
    int id;

    @SerializedName("Name")
    @Expose
    String name;

    public static final Parcelable.Creator<DoctorResult> CREATOR = new Parcelable.Creator<DoctorResult>() {
        @Override
        public DoctorResult createFromParcel(Parcel source) {
            return new DoctorResult(source);
        }

        @Override
        public DoctorResult[] newArray(int size) {
            return new DoctorResult[size];
        }
    };
    @SerializedName("Gender")
    @Expose
    boolean gender;
    @SerializedName("Nationality")
    @Expose
    List<String> nationalityList;
    @SerializedName("Status")
    @Expose
    String status;

    @SerializedName("Title")
    @Expose
    String title;

    @SerializedName("Qualification")
    @Expose
    String qualification;
    @SerializedName("Language")
    @Expose
    List<String> languageList;
    @SerializedName("Speciality")
    @Expose
    String speciality;
    @SerializedName("rating")
    @Expose
    int ratting;
    @SerializedName("NumberOfRaters")
    @Expose
    int numberOfRaters;
    @SerializedName("IsFav")
    @Expose
    boolean isFav;
    @SerializedName("Email")
    @Expose
    String mail;
    @SerializedName("ContactNumber")
    @Expose
    String phoneNumber;

    @SerializedName("Address")
    @Expose
    String address;
    @SerializedName("Reviews")
    @Expose
    List<Review> reviewList;
    @SerializedName("FacilityName")
    @Expose
    String facilityName;
    @SerializedName("FacilityId")
    @Expose
    int facilityId;
    @SerializedName("SpecialityId")
    @Expose
    int specialityId;

    @SerializedName("Info")
    @Expose
    String info;

    @SerializedName("FacilityLocation")
    @Expose
    String facilityLocation;

    @SerializedName("FacilityInfo")
    @Expose
    String facilityInfo;

    @SerializedName("ClinicInfo")
    @Expose
    String clinicInfo;
    @SerializedName("ImageUrl")
    @Expose
    String imageUrl;
    @SerializedName("DoctorFacilities")
    @Expose
    List<FacilityResult> facilityResultList;
    @SerializedName("AppointmentRemainder")
    @Expose
    boolean appointmentRemainder;
    @SerializedName("EnableNotification")
    @Expose
    boolean enableNotification;
    @SerializedName("Area")
    @Expose
    String area;

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("DoctorTypeId")
    @Expose
    int doctorTypeId;

    public DoctorResult() {
    }

    protected DoctorResult(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.gender = in.readByte() != 0;
        this.nationalityList = in.createStringArrayList();
        this.status = in.readString();
        this.languageList = in.createStringArrayList();
        this.title = in.readString();
        this.qualification = in.readString();
        this.speciality = in.readString();
        this.ratting = in.readInt();
        this.numberOfRaters = in.readInt();
        this.isFav = in.readByte() != 0;
        this.mail = in.readString();
        this.phoneNumber = in.readString();
        this.reviewList = in.createTypedArrayList(Review.CREATOR);
        this.address = in.readString();
        this.facilityName = in.readString();
        this.facilityId = in.readInt();
        this.specialityId = in.readInt();
        this.imageUrl = in.readString();
        this.info = in.readString();
        this.facilityLocation = in.readString();
        this.facilityInfo = in.readString();
        this.clinicInfo = in.readString();
        this.facilityResultList = in.createTypedArrayList(FacilityResult.CREATOR);
        this.appointmentRemainder = in.readByte() != 0;
        this.enableNotification = in.readByte() != 0;
        this.area = in.readString();
        this.doctorTypeId = in.readInt();
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<String> getNationalityList() {
        return nationalityList;
    }

    public void setNationalityList(List<String> nationalityList) {
        this.nationalityList = nationalityList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<String> languageList) {
        this.languageList = languageList;
    }

    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }

    public int getNumberOfRaters() {
        return numberOfRaters;
    }

    public void setNumberOfRaters(int numberOfRaters) {
        this.numberOfRaters = numberOfRaters;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFacilityLocation() {
        return facilityLocation;
    }

    public void setFacilityLocation(String facilityLocation) {
        this.facilityLocation = facilityLocation;
    }

    public String getFacilityInfo() {
        return facilityInfo;
    }

    public void setFacilityInfo(String facilityInfo) {
        this.facilityInfo = facilityInfo;
    }

    public String getClinicInfo() {
        return clinicInfo;
    }

    public void setClinicInfo(String clinicInfo) {
        this.clinicInfo = clinicInfo;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<FacilityResult> getFacilityResultList() {
        return facilityResultList;
    }

    public void setFacilityResultList(List<FacilityResult> facilityResultList) {
        this.facilityResultList = facilityResultList;
    }

    public int getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(int doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public boolean isAppointmentRemainder() {
        return appointmentRemainder;
    }

    public void setAppointmentRemainder(boolean appointmentRemainder) {
        this.appointmentRemainder = appointmentRemainder;
    }

    public boolean isEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        this.enableNotification = enableNotification;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeByte(this.gender ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.nationalityList);
        dest.writeString(this.status);
        dest.writeStringList(this.languageList);
        dest.writeString(this.title);
        dest.writeString(this.qualification);
        dest.writeString(this.speciality);
        dest.writeInt(this.ratting);
        dest.writeInt(this.numberOfRaters);
        dest.writeByte(this.isFav ? (byte) 1 : (byte) 0);
        dest.writeString(this.mail);
        dest.writeString(this.phoneNumber);
        dest.writeTypedList(this.reviewList);
        dest.writeString(this.address);
        dest.writeString(this.facilityName);
        dest.writeInt(this.facilityId);
        dest.writeInt(this.specialityId);
        dest.writeString(this.imageUrl);
        dest.writeString(this.info);
        dest.writeString(this.facilityLocation);
        dest.writeString(this.facilityInfo);
        dest.writeString(this.clinicInfo);
        dest.writeTypedList(this.facilityResultList);
        dest.writeByte(this.appointmentRemainder ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enableNotification ? (byte) 1 : (byte) 0);
        dest.writeString(this.area);
        dest.writeInt(this.doctorTypeId);
    }
}
