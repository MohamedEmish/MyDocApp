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
    int appointmentId;
    @SerializedName("PatientId")
    @Expose
    int patientId;
    @SerializedName("ContactNumber")
    @Expose
    String patientPhone;
    @SerializedName("FacilityId")
    @Expose
    int doctorFacilityId;
    @SerializedName("SpecialtyId")
    @Expose
    int specialtyId;
    @SerializedName("DoctorId")
    @Expose
    int doctorId;
    @SerializedName("DateTime")
    @Expose
    String data;
    @SerializedName("PromoCode")
    @Expose
    String promoCode;
    @SerializedName("NationalId")
    @Expose
    String nationalId;
    @SerializedName("Time")
    @Expose
    String time;
    @SerializedName("RequestType")
    @Expose
    int requestType;

    public AppointmentToUpload() {
    }

    protected AppointmentToUpload(Parcel in) {
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

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public int getDoctorFacilityId() {
        return doctorFacilityId;
    }

    public void setDoctorFacilityId(int doctorFacilityId) {
        this.doctorFacilityId = doctorFacilityId;
    }

    public int getSpecialtyId() {
        return specialtyId;
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

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getNationalId() {
        return nationalId;
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

    public int getRequestType() {
        return requestType;
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