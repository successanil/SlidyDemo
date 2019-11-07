/*
 * Copyright (c) 2019. Relsell Global
 */

package com.stripedemo.geofiremod;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationObject implements Parcelable {
    String locationName;
    float latitude;
    float longitude;

    protected LocationObject(Parcel in) {
        locationName = in.readString();
        latitude = in.readFloat();
        longitude = in.readFloat();
    }

    public static final Creator<LocationObject> CREATOR = new Creator<LocationObject>() {
        @Override
        public LocationObject createFromParcel(Parcel in) {
            return new LocationObject(in);
        }

        @Override
        public LocationObject[] newArray(int size) {
            return new LocationObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(locationName);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
    }
}
