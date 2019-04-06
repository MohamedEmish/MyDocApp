package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FacilityResult implements Parcelable {

    public static final Creator<FacilityResult> CREATOR = new Creator<FacilityResult>() {
        @Override
        public FacilityResult createFromParcel(Parcel source) {
            return new FacilityResult(source);
        }

        @Override
        public FacilityResult[] newArray(int size) {
            return new FacilityResult[size];
        }
    };
    @SerializedName("Id")
    @Expose
    int id;
    @SerializedName("Name")
    @Expose
    private
    String name;
    @SerializedName("Address")
    @Expose
    private
    String address;
    @SerializedName("Rating")
    @Expose
    private
    int rating;
    @SerializedName("ContactNumber")
    @Expose
    private
    String phoneNumber;
    @SerializedName("Location")
    @Expose
    private
    String location;
    @SerializedName("WebSite")
    @Expose
    private
    String webSite;
    @SerializedName("IsFav")
    @Expose
    private
    boolean isFav;
    @SerializedName("Doctors")
    @Expose
    private
    List<DoctorResult> doctorsList;
    @SerializedName("InsuranceCompanies")
    @Expose
    private
    List<Insurance> insuranceList;
    @SerializedName("ImageUrl")
    @Expose
    private
    String ImageUrl;
    @SerializedName("Images")
    @Expose
    private
    List<String> bannersUrlList;
    @SerializedName("GeneralInfo")
    @Expose
    private
    String generalInfo;
    @SerializedName("Services")
    @Expose
    private
    String services;
    @SerializedName("FacilitySpecialities")
    @Expose
    private
    List<Speciality> specialityList;
    @SerializedName("Area")
    @Expose
    private
    String area;
    @SerializedName("Lang")
    @Expose
    private
    String language;
    @SerializedName("AreaId")
    @Expose
    private
    int areaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @SerializedName("Email")
    @Expose
    private
    String email;

    private FacilityResult(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.address = in.readString();
        this.rating = in.readInt();
        this.phoneNumber = in.readString();
        this.location = in.readString();
        this.webSite = in.readString();
        this.isFav = in.readByte() != 0;
        this.doctorsList = new ArrayList<>();
        in.readList(this.doctorsList, DoctorResult.class.getClassLoader());
        this.insuranceList = in.createTypedArrayList(Insurance.CREATOR);
        this.ImageUrl = in.readString();
        this.bannersUrlList = in.createStringArrayList();
        this.generalInfo = in.readString();
        this.services = in.readString();
        this.specialityList = in.createTypedArrayList(Speciality.CREATOR);
        this.area = in.readString();
        this.language = in.readString();
        this.areaId = in.readInt();
        this.email = in.readString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebSite() {
        return webSite;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public List<DoctorResult> getDoctorsList() {
        return doctorsList;
    }

    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    public String getArea() {
        return area;
    }

    public List<Speciality> getSpecialityList() {
        return specialityList;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeInt(this.rating);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.location);
        dest.writeString(this.webSite);
        dest.writeByte(this.isFav ? (byte) 1 : (byte) 0);
        dest.writeList(this.doctorsList);
        dest.writeTypedList(this.insuranceList);
        dest.writeString(this.ImageUrl);
        dest.writeStringList(this.bannersUrlList);
        dest.writeString(this.generalInfo);
        dest.writeString(this.services);
        dest.writeTypedList(this.specialityList);
        dest.writeString(this.area);
        dest.writeString(this.language);
        dest.writeInt(this.areaId);
        dest.writeString(this.email);
    }
}
