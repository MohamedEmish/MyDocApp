package com.yackeenSolution.mydocapp.Objects;

public class Appointment {
    private int id;
    private String doctorName;
    private int bookingNumber;
    private String speciality;
    private String facility;
    private String date;
    private String time;
    private int phoneNumber;
    private String direction;

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
}
