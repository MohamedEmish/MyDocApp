package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserToken implements Parcelable {
    public static final Parcelable.Creator<UserToken> CREATOR = new Parcelable.Creator<UserToken>() {
        @Override
        public UserToken createFromParcel(Parcel source) {
            return new UserToken(source);
        }

        @Override
        public UserToken[] newArray(int size) {
            return new UserToken[size];
        }
    };
    @SerializedName("UserId")
    @Expose
    int id;
    @SerializedName("FBId")
    @Expose
    private
    String fBId;
    @SerializedName("Email")
    @Expose
    private
    String email;
    @SerializedName("Password")
    @Expose
    private
    String password;
    @SerializedName("DeviceToken")
    @Expose
    private
    String deviceToken;

    public UserToken() {
    }

    private UserToken(Parcel in) {
        this.id = in.readInt();
        this.fBId = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.deviceToken = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.fBId);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.deviceToken);
    }
}