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
    String email;
    @SerializedName("VerfiyCode")
    @Expose
    String code;
    @SerializedName("NewPassword")
    @Expose
    String newPassword;

    public PasswordToken() {
    }

    protected PasswordToken(Parcel in) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNewPassword() {
        return newPassword;
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