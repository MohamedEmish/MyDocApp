package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmallAppointment implements Parcelable {
    public static final Parcelable.Creator<SmallAppointment> CREATOR = new Parcelable.Creator<SmallAppointment>() {
        @Override
        public SmallAppointment createFromParcel(Parcel source) {
            return new SmallAppointment(source);
        }

        @Override
        public SmallAppointment[] newArray(int size) {
            return new SmallAppointment[size];
        }
    };
    @SerializedName("AppointmentId")
    @Expose
    private int appointmentId;
    @SerializedName("StatusId")
    @Expose
    private int statusId;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("DetailedStatusId")
    @Expose
    private int detailedStatusId;
    @SerializedName("SlotId")
    @Expose
    private int slotId;

    public SmallAppointment() {
    }

    private SmallAppointment(Parcel in) {
        this.appointmentId = in.readInt();
        this.statusId = in.readInt();
        this.date = in.readString();
        this.detailedStatusId = in.readInt();
        this.slotId = in.readInt();
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDetailedStatusId() {
        return detailedStatusId;
    }

    public void setDetailedStatusId(int detailedStatusId) {
        this.detailedStatusId = detailedStatusId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.appointmentId);
        dest.writeInt(this.statusId);
        dest.writeString(this.date);
        dest.writeInt(this.detailedStatusId);
        dest.writeInt(this.slotId);
    }
}