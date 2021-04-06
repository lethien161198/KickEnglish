package com.example.myapplication.Models.Authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInRequest {
    @SerializedName("username")
    @Expose
    private String user;
    @SerializedName("password")
    @Expose
    private String pass;

    public SignInRequest(String pass, String user) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
