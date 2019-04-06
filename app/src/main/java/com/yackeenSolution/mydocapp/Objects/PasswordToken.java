package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PasswordToken implements Parcelable {

    public static final Parcelable.Creator<PasswordToken> CREATOR = new Parcelable.Creator<PasswordToken>() {
        @Override
        public PasswordToken createFromParcel(Parcel source) {
            return new PasswordToken(source);
        }

        @Override
        public PasswordToken[] newArray(int size) {
            return new PasswordToken[size];
        }
    };
    @SerializedName("Email")
    @Expose
    private
    String email;
    @SerializedName("VerfiyCode")
    @Expose
    private
    String code;
    @SerializedName("NewPassword")
    @Expose
    private
    String newPassword;

    public PasswordToken() {
    }

    private PasswordToken(Parcel in) {
        this.email = in.readString();
        this.code = in.readString();
        this.newPassword = in.readString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.code);
        dest.writeString(this.newPassword);
    }
}