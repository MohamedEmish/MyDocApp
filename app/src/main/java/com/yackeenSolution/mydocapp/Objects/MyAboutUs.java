package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyAboutUs implements Parcelable {

    public static final Parcelable.Creator<MyAboutUs> CREATOR = new Parcelable.Creator<MyAboutUs>() {
        @Override
        public MyAboutUs createFromParcel(Parcel source) {
            return new MyAboutUs(source);
        }

        @Override
        public MyAboutUs[] newArray(int size) {
            return new MyAboutUs[size];
        }
    };
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("Title")
    @Expose
    private String title;

    private MyAboutUs(Parcel in) {
        this.content = in.readString();
        this.title = in.readString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.title);
    }
}
