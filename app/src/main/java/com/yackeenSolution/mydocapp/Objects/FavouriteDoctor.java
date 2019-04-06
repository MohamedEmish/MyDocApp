package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavouriteDoctor implements Parcelable {

    public static final Creator<FavouriteDoctor> CREATOR = new Creator<FavouriteDoctor>() {
        @Override
        public FavouriteDoctor createFromParcel(Parcel source) {
            return new FavouriteDoctor(source);
        }

        @Override
        public FavouriteDoctor[] newArray(int size) {
            return new FavouriteDoctor[size];
        }
    };
    @SerializedName("Id")
    @Expose
    int id;
    @SerializedName("Name")
    @Expose
    private
    String name;
    @SerializedName("PhoneNumber")
    @Expose
    private
    String phoneNumber;
    @SerializedName("Gender")
    @Expose
    private
    String gender;
    @SerializedName("Nationality")
    @Expose
    private
    List<String> nationality;
    @SerializedName("Language")
    @Expose
    private
    List<String> languages;
    @SerializedName("Title")
    @Expose
    private
    String title;
    @SerializedName("Qualification")
    @Expose
    private
    String qualification;
    @SerializedName("Specialty")
    @Expose
    private
    String speciality;
    @SerializedName("SpecialtyId")
    @Expose
    private
    int specialityId;
    @SerializedName("Rating")
    @Expose
    private
    int rating;
    @SerializedName("Area")
    @Expose
    private
    String area;
    @SerializedName("FacilityId")
    @Expose
    private
    int facilityId;
    @SerializedName("FacilityName")
    @Expose
    private
    String facilityName;
    @SerializedName("Address")
    @Expose
    private
    String address;
    @SerializedName("IsFav")
    @Expose
    private
    boolean isFav;
    @SerializedName("ImageUrl")
    @Expose
    private
    String ImageUrl;
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
    @SerializedName("DoctorTypeId")
    @Expose
    private
    int doctorTypeId;
    @SerializedName("NextDateTime")
    @Expose
    private
    String nextDate;
    @SerializedName("GetDoctorSlots")
    @Expose
    private
    List<DoctorSlots> doctorSlots;

    private FavouriteDoctor(Parcel in) {
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

    public String getTitle() {
        return title;
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

    public int getId() {
        return id;
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

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getAddress() {
        return address;
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

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int describeContents() {
        return 0;
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
