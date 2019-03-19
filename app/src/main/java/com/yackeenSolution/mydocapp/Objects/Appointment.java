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
    int id;
    @SerializedName("FacilityId")
    @Expose
    int facilityId;
    @SerializedName("DoctorId")
    @Expose
    int doctorId;
    @SerializedName("PatientName")
    @Expose
    String patientName;
    @SerializedName("DoctorName")
    @Expose
    String doctorName;
    @SerializedName("Specialty")
    @Expose
    String speciality;
    @SerializedName("ClinicName")
    @Expose
    String clinicName;
    @SerializedName("DateTime")
    @Expose
    String dateTime;
    @SerializedName("ClinicLocation")
    @Expose
    String clinicLocation;
    @SerializedName("ClinicContactNumber")
    @Expose
    String phoneNumber;
    @SerializedName("RequestType")
    @Expose
    int requestType;
    @SerializedName("Time")
    @Expose
    String time;
    @SerializedName("AppointmentStatus")
    @Expose
    String appointmentStatus;
    @SerializedName("DetailedStatusId")
    @Expose
    int detailedStatusId;
    @SerializedName("DetailedStatus")
    @Expose
    String detailedStatus;
    @SerializedName("IsRated")
    @Expose
    boolean isRated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("IsSlot")
    @Expose
    int isSlot;

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

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(String clinicLocation) {
        this.clinicLocation = clinicLocation;
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

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public int getDetailedStatusId() {
        return detailedStatusId;
    }

    public void setDetailedStatusId(int detailedStatusId) {
        this.detailedStatusId = detailedStatusId;
    }

    public String getDetailedStatus() {
        return detailedStatus;
    }

    public void setDetailedStatus(String detailedStatus) {
        this.detailedStatus = detailedStatus;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getIsSlot() {
        return isSlot;
    }

    public void setIsSlot(int isSlot) {
        this.isSlot = isSlot;
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
