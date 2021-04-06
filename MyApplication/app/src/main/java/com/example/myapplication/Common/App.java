package com.example.myapplication.Common;

import android.app.Application;

import com.google.gson.Gson;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceHelper.initialize(getApplicationContext());
        //RealPath.initialize(getApplicationContext());
    }
}
