package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class MyNotification implements Parcelable {

    public static final Parcelable.Creator<MyNotification> CREATOR = new Parcelable.Creator<MyNotification>() {
        @Override
        public MyNotification createFromParcel(Parcel source) {
            return new MyNotification(source);
        }

        @Override
        public MyNotification[] newArray(int size) {
            return new MyNotification[size];
        }
    };
    private int id;
    private String title;

    // TODO: Remove this Constructor after clear Dummy Data and ((API)) connection
    public MyNotification(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public MyNotification() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    protected MyNotification(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
    }
}
