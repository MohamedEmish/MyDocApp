package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewFavDoctor implements Parcelable {
    public static final Parcelable.Creator<NewFavDoctor> CREATOR = new Parcelable.Creator<NewFavDoctor>() {
        @Override
        public NewFavDoctor createFromParcel(Parcel source) {
            return new NewFavDoctor(source);
        }

        @Override
        public NewFavDoctor[] newArray(int size) {
            return new NewFavDoctor[size];
        }
    };
    @SerializedName("UserId")
    @Expose
    int userId;
    @SerializedName("DoctorId")
    @Expose
    int doctorId;
    @SerializedName("FacilityId")
    @Expose
    int facilityId;
    @SerializedName("IsFav")
    @Expose
    Boolean isFav;

    public NewFavDoctor() {
    }

    protected NewFavDoctor(Parcel in) {
        this.userId = in.readInt();
        this.doctorId = in.readInt();
        this.facilityId = in.readInt();
        this.isFav = (Boolean) in.readValue(Boolean.class.getClassLoader());
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

    public Boolean getFav() {
        return isFav;
    }

    public void setFav(Boolean fav) {
        isFav = fav;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeInt(this.doctorId);
        dest.writeInt(this.facilityId);
        dest.writeValue(this.isFav);
    }
}
