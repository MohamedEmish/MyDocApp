package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorSlots implements Parcelable {

    public static final Parcelable.Creator<DoctorSlots> CREATOR = new Parcelable.Creator<DoctorSlots>() {
        @Override
        public DoctorSlots createFromParcel(Parcel source) {
            return new DoctorSlots(source);
        }

        @Override
        public DoctorSlots[] newArray(int size) {
            return new DoctorSlots[size];
        }
    };
    @SerializedName("SlotId")
    @Expose
    int slotId;
    @SerializedName("DoctorId")
    @Expose
    int doctorId;
    @SerializedName("From")
    @Expose
    String from;
    @SerializedName("To")
    @Expose
    String to;
    @SerializedName("Date")
    @Expose
    String date;

    public DoctorSlots() {
    }

    protected DoctorSlots(Parcel in) {
        this.slotId = in.readInt();
        this.doctorId = in.readInt();
        this.from = in.readString();
        this.to = in.readString();
        this.date = in.readString();
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.slotId);
        dest.writeInt(this.doctorId);
        dest.writeString(this.from);
        dest.writeString(this.to);
        dest.writeString(this.date);
    }
}
