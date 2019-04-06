package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Promotion implements Parcelable {

    public static final Parcelable.Creator<Promotion> CREATOR = new Parcelable.Creator<Promotion>() {
        @Override
        public Promotion createFromParcel(Parcel source) {
            return new Promotion(source);
        }

        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };
    @SerializedName("Id")
    @Expose
    int id;
    @SerializedName("Name")
    @Expose
    private
    String name;
    @SerializedName("Description")
    @Expose
    private
    String description;
    @SerializedName("PromoCode")
    @Expose
    private
    String promoCode;
    @SerializedName("SpecialityId")
    @Expose
    private
    int specialityId;
    @SerializedName("FacilityId")
    @Expose
    private
    int facilityId;
    @SerializedName("ExpiryDate")
    @Expose
    private
    String expiryDate;
    @SerializedName("Doctors")
    @Expose
    private
    List<FavouriteDoctor> doctorsList;

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("ImageUrl")
    @Expose
    private
    String imageUrl;

    private Promotion(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.promoCode = in.readString();
        this.specialityId = in.readInt();
        this.facilityId = in.readInt();
        this.expiryDate = in.readString();
        this.doctorsList = in.createTypedArrayList(FavouriteDoctor.CREATOR);
        this.imageUrl = in.readString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.promoCode);
        dest.writeInt(this.specialityId);
        dest.writeInt(this.facilityId);
        dest.writeString(this.expiryDate);
        dest.writeTypedList(this.doctorsList);
        dest.writeString(this.imageUrl);
    }
}