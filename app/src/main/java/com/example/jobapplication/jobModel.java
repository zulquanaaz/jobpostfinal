package com.example.jobapplication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class jobModel implements Parcelable{

    @SerializedName("company")

    String company;

    @SerializedName("designation")

    String designation;

    @SerializedName("roles")

    String roles;


    protected jobModel(Parcel in) {
        company = in.readString();
        designation = in.readString();
        roles = in.readString();


    }

    public static final Parcelable.Creator<jobModel> CREATOR = new Parcelable.Creator<jobModel>() {
        @Override
        public jobModel createFromParcel(Parcel in) {
            return new jobModel(in);
        }

        @Override
        public jobModel[] newArray(int size) {
            return new jobModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(company);
        parcel.writeString(designation);
        parcel.writeString(roles);


    }


}
