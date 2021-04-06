package com.example.myapplication.Models.Authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("firebaseToken")
    @Expose
    private String firebaseToken;

    public Token(String deviceType, String firebaseToken) {
        this.deviceType = deviceType;
        this.firebaseToken = firebaseToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
