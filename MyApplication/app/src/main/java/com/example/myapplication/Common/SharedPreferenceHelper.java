package com.example.myapplication.Common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.Models.Setting.UserDTO;
import com.google.gson.Gson;

public class SharedPreferenceHelper {
    private static final String PREFS_NAME = "share_prefs";
    private static SharedPreferenceHelper mInstance;
    private static SharedPreferences mSharedPreferences;

    private SharedPreferenceHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public synchronized static void initialize(Context context) {
        if (context == null) {
            throw new NullPointerException("ApplicationContext is null");
        }
        if (mInstance == null) {
            synchronized ((SharedPreferenceHelper.class)) {
                if (mInstance == null) {
                    mInstance = new SharedPreferenceHelper(context);
                }
            }
        }

    }

    private static SharedPreferences getPrefs() {
        return mSharedPreferences;
    }

    public static void clearPrefs() {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.clear();
        editor.apply();
    }

    public static void removeKey(String key) {
        getPrefs().edit().remove(key).commit();
    }

    public static boolean containsKey(String key) {
        return getPrefs().contains(key);
    }

    public static String getString(String key, String defValue) {
        return getPrefs().getString(key, defValue);
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int getInt(String key, int defValue) {
        return getPrefs().getInt(key, defValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static void setInt(String key, int value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static long getLong(String key, long defValue) {
        return getPrefs().getLong(key, defValue);
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static void setLong(String key, long value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getPrefs().getBoolean(key, defValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getFloat(String key) {
        return getFloat(key, 0f);
    }

    public static boolean getFloat(String key, float defValue) {
        return getFloat(key, defValue);
    }

    public static void setFloat(String key, Float value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void setUser(String key, UserDTO userDTO) {
        SharedPreferences.Editor editor = getPrefs().edit();
        Gson gson = new Gson();
        String json = gson.toJson(userDTO);
        editor.putString(key, json);
        editor.apply();
    }

    public static UserDTO getUser(String key) {
        Gson gson = new Gson();
        String json = getPrefs().getString(key, "");
        UserDTO obj = gson.fromJson(json, UserDTO.class);
        return obj;
    }
}
