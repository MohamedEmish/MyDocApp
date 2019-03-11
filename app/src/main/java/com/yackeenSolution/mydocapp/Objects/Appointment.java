package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Appointment implements Parcelable {
    private int id;
    private String doctorName;
    private int bookingNumber;
    private String speciality;
    private String facility;
    private String date;
    private String time;
    private int phoneNumber;
    private String direction;

    // TODO: Remove this Constructor after clear Dummy Data and ((API)) connection
    public Appointment(String doctorName, int appointmentNumber, String speciality, String place, String date, String time, int phoneNumber, String direction) {
        this.doctorName = doctorName;
        this.bookingNumber = appointmentNumber;
        this.speciality = speciality;
        this.facility = place;
        this.date = date;
        this.time = time;
        this.phoneNumber = phoneNumber;
        this.direction = direction;
    }

    public Appointment() {
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getFacility() {
        return facility;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getDirection() {
        return direction;
    }

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

    protected Appointment(Parcel in) {
        this.id = in.readInt();
        this.doctorName = in.readString();
        this.bookingNumber = in.readInt();
        this.speciality = in.readString();
        this.facility = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.phoneNumber = in.readInt();
        this.direction = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.doctorName);
        dest.writeInt(this.bookingNumber);
        dest.writeString(this.speciality);
        dest.writeString(this.facility);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeInt(this.phoneNumber);
        dest.writeString(this.direction);
    }
}
