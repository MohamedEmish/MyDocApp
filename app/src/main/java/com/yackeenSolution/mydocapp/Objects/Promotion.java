package com.yackeenSolution.mydocapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Promotion implements Parcelable {
    public static final Parcelable.Creator<Promotion> CREATOR = new Parcelable.Creator<Promotion>() {
        @Override
        public Promotion createFromParcel(Parcel source) {
            return new Promotion(source);
        }

        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };
    private int id;
    private String mainText;
    private String subText;
    private String imageUri;

    public Promotion() {
    }

    // TODO: Remove this Constructor after clear Dummy Data and ((API)) connection
    public Promotion(String mainText, String subText, String imageUri) {
        this.mainText = mainText;
        this.subText = subText;
        this.imageUri = imageUri;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    protected Promotion(Parcel in) {
        this.id = in.readInt();
        this.mainText = in.readString();
        this.subText = in.readString();
        this.imageUri = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainText() {
        return mainText;
    }

    public String getSubText() {
        return subText;
    }

    public String getImageUri() {
        return imageUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.mainText);
        dest.writeString(this.subText);
        dest.writeString(this.imageUri);
    }
}
