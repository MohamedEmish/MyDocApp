package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Advice implements Parcelable {

    @SerializedName("Content")
    @Expose
    private
    String message;
    @SerializedName("Email")
    @Expose
    private
    String mail;
    @SerializedName("PhoneNumber")
    @Expose
    private
    String phone;

    public static final Parcelable.Creator<Advice> CREATOR = new Parcelable.Creator<Advice>() {
        @Override
        public Advice createFromParcel(Parcel source) {
            return new Advice(source);
        }

        @Override
        public Advice[] newArray(int size) {
            return new Advice[size];
        }
    };

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.mail);
        dest.writeString(this.phone);
    }

    public Advice() {
    }

    private Advice(Parcel in) {
        this.message = in.readString();
        this.mail = in.readString();
        this.phone = in.readString();
    }
}
