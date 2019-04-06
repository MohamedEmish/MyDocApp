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
    private
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
    private
    boolean gender;
    @SerializedName("Nationality")
    @Expose
    private
    List<String> nationalityList;
    @SerializedName("Status")
    @Expose
    private
    String status;
    @SerializedName("Title")
    @Expose
    private
    String title;
    @SerializedName("Qualification")
    @Expose
    private
    String qualification;
    @SerializedName("Language")
    @Expose
    private
    List<String> languageList;
    @SerializedName("Speciality")
    @Expose
    private
    String speciality;
    @SerializedName("rating")
    @Expose
    private
    int ratting;
    @SerializedName("NumberOfRaters")
    @Expose
    private
    int numberOfRaters;
    @SerializedName("IsFav")
    @Expose
    private
    boolean isFav;
    @SerializedName("Email")
    @Expose
    private
    String mail;
    @SerializedName("ContactNumber")
    @Expose
    private
    String phoneNumber;
    @SerializedName("Address")
    @Expose
    private
    String address;
    @SerializedName("Reviews")
    @Expose
    private
    List<Review> reviewList;
    @SerializedName("FacilityName")
    @Expose
    private
    String facilityName;
    @SerializedName("FacilityId")
    @Expose
    private
    int facilityId;
    @SerializedName("SpecialityId")
    @Expose
    private
    int specialityId;
    @SerializedName("Info")
    @Expose
    private
    String info;
    @SerializedName("FacilityLocation")
    @Expose
    private
    String facilityLocation;
    @SerializedName("FacilityInfo")
    @Expose
    private
    String facilityInfo;
    @SerializedName("ClinicInfo")
    @Expose
    private
    String clinicInfo;
    @SerializedName("ImageUrl")
    @Expose
    private
    String imageUrl;
    @SerializedName("DoctorFacilities")
    @Expose
    private
    List<FacilityResult> facilityResultList;
    @SerializedName("AppointmentRemainder")
    @Expose
    private
    boolean appointmentRemainder;
    @SerializedName("EnableNotification")
    @Expose
    private
    boolean enableNotification;
    @SerializedName("Area")
    @Expose
    private
    String area;
    public void setName(String name) {
        this.name = name;
    }
    @SerializedName("DoctorTypeId")
    @Expose
    private
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

    public String getQualification() {
        return qualification;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getInfo() {
        return info;
    }

    public String getFacilityLocation() {
        return facilityLocation;
    }

    public String getClinicInfo() {
        return clinicInfo;
    }

    public String getFacilityName() {
        return facilityName;
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
