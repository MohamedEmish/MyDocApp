package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoctorResult implements Parcelable {

    public static final Creator<DoctorResult> CREATOR = new Creator<DoctorResult>() {
        @Override
        public DoctorResult createFromParcel(Parcel source) {
            return new DoctorResult(source);
        }

        @Override
        public DoctorResult[] newArray(int size) {
            return new DoctorResult[size];
        }
    };
    @SerializedName("Id")
    @Expose
    int id;
    @SerializedName("Name")
    @Expose
    String name;
    @SerializedName("PhoneNumber")
    @Expose
    String phoneNumber;
    @SerializedName("Gender")
    @Expose
    String gender;
    @SerializedName("Nationality")
    @Expose
    List<String> nationality;
    @SerializedName("Language")
    @Expose
    List<String> languages;
    @SerializedName("Title")
    @Expose
    String title;
    @SerializedName("Qualification")
    @Expose
    String qualification;
    @SerializedName("Specialty")
    @Expose
    String speciality;
    @SerializedName("SpecialtyId")
    @Expose
    int specialityId;
    @SerializedName("Rating")
    @Expose
    int rating;
    @SerializedName("Area")
    @Expose
    String area;
    @SerializedName("FacilityId")
    @Expose
    int facilityId;
    @SerializedName("FacilityName")
    @Expose
    String facilityName;
    @SerializedName("Address")
    @Expose
    String address;
    @SerializedName("IsFav")
    @Expose
    boolean isFav;
    @SerializedName("ImageUrl")
    @Expose
    String ImageUrl;
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
    @SerializedName("DoctorTypeId")
    @Expose
    int doctorTypeId;
    @SerializedName("NextDateTime")
    @Expose
    String nextDate;
    @SerializedName("GetDoctorSlots")
    @Expose
    List<DoctorSlots> doctorSlots;

    public void setName(String name) {
        this.name = name;
    }

    public DoctorResult() {
    }

    protected DoctorResult(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.phoneNumber = in.readString();
        this.gender = in.readString();
        this.nationality = in.createStringArrayList();
        this.languages = in.createStringArrayList();
        this.title = in.readString();
        this.qualification = in.readString();
        this.speciality = in.readString();
        this.specialityId = in.readInt();
        this.rating = in.readInt();
        this.area = in.readString();
        this.facilityId = in.readInt();
        this.facilityName = in.readString();
        this.address = in.readString();
        this.isFav = in.readByte() != 0;
        this.ImageUrl = in.readString();
        this.info = in.readString();
        this.facilityLocation = in.readString();
        this.facilityInfo = in.readString();
        this.clinicInfo = in.readString();
        this.doctorTypeId = in.readInt();
        this.nextDate = in.readString();
        this.doctorSlots = in.createTypedArrayList(DoctorSlots.CREATOR);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getNationality() {
        return nationality;
    }

    public void setNationality(List<String> nationality) {
        this.nationality = nationality;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
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

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(boolean isFav) {
        this.isFav = isFav;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getArea() {
        return area;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
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

    public int getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(int doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<DoctorSlots> getDoctorSlots() {
        return doctorSlots;
    }

    public void setDoctorSlots(List<DoctorSlots> doctorSlots) {
        this.doctorSlots = doctorSlots;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.gender);
        dest.writeStringList(this.nationality);
        dest.writeStringList(this.languages);
        dest.writeString(this.title);
        dest.writeString(this.qualification);
        dest.writeString(this.speciality);
        dest.writeInt(this.specialityId);
        dest.writeInt(this.rating);
        dest.writeString(this.area);
        dest.writeInt(this.facilityId);
        dest.writeString(this.facilityName);
        dest.writeString(this.address);
        dest.writeByte(this.isFav ? (byte) 1 : (byte) 0);
        dest.writeString(this.ImageUrl);
        dest.writeString(this.info);
        dest.writeString(this.facilityLocation);
        dest.writeString(this.facilityInfo);
        dest.writeString(this.clinicInfo);
        dest.writeInt(this.doctorTypeId);
        dest.writeString(this.nextDate);
        dest.writeTypedList(this.doctorSlots);
    }
}
