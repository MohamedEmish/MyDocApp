package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Appointment implements Parcelable {

    public static final Parcelable.Creator<Appointment> CREATOR = new Parcelable.Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel source) {
            return new Appointment(source);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };
    @SerializedName("Id")
    @Expose
    Integer id;
    @SerializedName("FacilityId")
    @Expose
    private
    Integer facilityId;
    @SerializedName("DoctorId")
    @Expose
    private
    Integer doctorId;
    @SerializedName("PatientName")
    @Expose
    private
    String patientName;
    @SerializedName("DoctorName")
    @Expose
    private
    String doctorName;
    @SerializedName("Specialty")
    @Expose
    private
    String speciality;
    @SerializedName("ClinicName")
    @Expose
    private
    String clinicName;
    @SerializedName("DateTime")
    @Expose
    private
    String dateTime;
    @SerializedName("ClinicLocation")
    @Expose
    private
    String clinicLocation;
    @SerializedName("ClinicContactNumber")
    @Expose
    private
    String phoneNumber;
    @SerializedName("RequestType")
    @Expose
    private
    Integer requestType;
    @SerializedName("Time")
    @Expose
    private
    String time;
    @SerializedName("AppointmentStatus")
    @Expose
    private
    String appointmentStatus;
    @SerializedName("DetailedStatusId")
    @Expose
    private
    Integer detailedStatusId;
    @SerializedName("DetailedStatus")
    @Expose
    private
    String detailedStatus;
    @SerializedName("IsRated")
    @Expose
    private
    Boolean isRated;
    @SerializedName("IsSlot")
    @Expose
    private
    Integer isSlot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Appointment() {
    }

    protected Appointment(Parcel in) {
        this.id = in.readInt();
        this.facilityId = in.readInt();
        this.doctorId = in.readInt();
        this.patientName = in.readString();
        this.doctorName = in.readString();
        this.speciality = in.readString();
        this.clinicName = in.readString();
        this.dateTime = in.readString();
        this.clinicLocation = in.readString();
        this.phoneNumber = in.readString();
        this.requestType = in.readInt();
        this.time = in.readString();
        this.appointmentStatus = in.readString();
        this.detailedStatusId = in.readInt();
        this.detailedStatus = in.readString();
        this.isRated = in.readByte() != 0;
        this.isSlot = in.readInt();
    }

    public int getFacilityId() {
        return facilityId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getClinicLocation() {
        return clinicLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTime() {
        return time;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.facilityId);
        dest.writeInt(this.doctorId);
        dest.writeString(this.patientName);
        dest.writeString(this.doctorName);
        dest.writeString(this.speciality);
        dest.writeString(this.clinicName);
        dest.writeString(this.dateTime);
        dest.writeString(this.clinicLocation);
        dest.writeString(this.phoneNumber);
        dest.writeInt(this.requestType);
        dest.writeString(this.time);
        dest.writeString(this.appointmentStatus);
        dest.writeInt(this.detailedStatusId);
        dest.writeString(this.detailedStatus);
        dest.writeByte(this.isRated ? (byte) 1 : (byte) 0);
        dest.writeInt(this.isSlot);
    }
}