package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Review implements Parcelable {

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel source) {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
    @SerializedName("Id")
    @Expose
    int id;
    @SerializedName("UserId")
    @Expose
    int userId;
    @SerializedName("DoctorId")
    @Expose
    int doctorId;
    @SerializedName("FacilityId")
    @Expose
    int facilityId;
    @SerializedName("Rate")
    @Expose
    int rate;
    @SerializedName("CreatingDate")
    @Expose
    String creatingDate;

    public Review() {
    }

    protected Review(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readInt();
        this.doctorId = in.readInt();
        this.facilityId = in.readInt();
        this.rate = in.readInt();
        this.creatingDate = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(String creatingDate) {
        this.creatingDate = creatingDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.userId);
        dest.writeInt(this.doctorId);
        dest.writeInt(this.facilityId);
        dest.writeInt(this.rate);
        dest.writeString(this.creatingDate);
    }
}
