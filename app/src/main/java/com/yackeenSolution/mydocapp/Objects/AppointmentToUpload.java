package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppointmentToUpload implements Parcelable {
    public static final Parcelable.Creator<AppointmentToUpload> CREATOR = new Parcelable.Creator<AppointmentToUpload>() {
        @Override
        public AppointmentToUpload createFromParcel(Parcel source) {
            return new AppointmentToUpload(source);
        }

        @Override
        public AppointmentToUpload[] newArray(int size) {
            return new AppointmentToUpload[size];
        }
    };
    @SerializedName("AppointmentId")
    @Expose
    private
    Integer appointmentId;
    @SerializedName("PatientId")
    @Expose
    private
    int patientId;
    @SerializedName("ContactNumber")
    @Expose
    private
    String patientPhone;
    @SerializedName("FacilityId")
    @Expose
    private
    int doctorFacilityId;
    @SerializedName("SpecialtyId")
    @Expose
    private
    int specialtyId;
    @SerializedName("DoctorId")
    @Expose
    private
    int doctorId;
    @SerializedName("DateTime")
    @Expose
    private
    String data;
    @SerializedName("PromoCode")
    @Expose
    private
    String promoCode;
    @SerializedName("NationalId")
    @Expose
    private
    String nationalId;
    @SerializedName("Time")
    @Expose
    private
    String time;
    @SerializedName("RequestType")
    @Expose
    private
    int requestType;

    public AppointmentToUpload() {
    }

    private AppointmentToUpload(Parcel in) {
        this.appointmentId = in.readInt();
        this.patientId = in.readInt();
        this.patientPhone = in.readString();
        this.doctorFacilityId = in.readInt();
        this.specialtyId = in.readInt();
        this.doctorId = in.readInt();
        this.data = in.readString();
        this.promoCode = in.readString();
        this.nationalId = in.readString();
        this.time = in.readString();
        this.requestType = in.readInt();
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public void setDoctorFacilityId(int doctorFacilityId) {
        this.doctorFacilityId = doctorFacilityId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.appointmentId);
        dest.writeInt(this.patientId);
        dest.writeString(this.patientPhone);
        dest.writeInt(this.doctorFacilityId);
        dest.writeInt(this.specialtyId);
        dest.writeInt(this.doctorId);
        dest.writeString(this.data);
        dest.writeString(this.promoCode);
        dest.writeString(this.nationalId);
        dest.writeString(this.time);
        dest.writeInt(this.requestType);
    }
}