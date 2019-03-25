package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewFavFacility implements Parcelable {

    public static final Parcelable.Creator<NewFavFacility> CREATOR = new Parcelable.Creator<NewFavFacility>() {
        @Override
        public NewFavFacility createFromParcel(Parcel source) {
            return new NewFavFacility(source);
        }

        @Override
        public NewFavFacility[] newArray(int size) {
            return new NewFavFacility[size];
        }
    };
    @SerializedName("UserId")
    @Expose
    int userId;
    @SerializedName("FacilityId")
    @Expose
    int facilityId;
    @SerializedName("IsFav")
    @Expose
    Boolean isFav;

    public NewFavFacility() {
    }

    protected NewFavFacility(Parcel in) {
        this.userId = in.readInt();
        this.facilityId = in.readInt();
        this.isFav = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        dest.writeInt(this.facilityId);
        dest.writeValue(this.isFav);
    }
}
