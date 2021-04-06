package com.example.myapplication.Common;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.example.myapplication.BuildConfig;

import java.util.Locale;

public class Utilities {
    public static final String URL = BuildConfig.BASE_URL + "api/v1/";
    public static final String SHARE_USER = "userDTO";
    public static final String SHARE_TOKEN = "token";
    public static final String SHARE_LANG = "lang";
    public static final String ID_ARTIST = "idArtist";
    public static final String ID_MEDIA = "idmediaDTO";
    public static final String EXTRA_PLAYAUDIO = "playaudio";
    public static final String EXTRA_MEDIA = "media";
    public static String token = null;

    public static void setLocale(String lang, Resources resources) {
        Locale myLocale = new Locale(lang);
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration conf = resources.getConfiguration();
        conf.locale = myLocale;
        resources.updateConfiguration(conf, dm);
    }

    public static int convertDpToPixels(float dp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
        return px;
    }

    public static long strToLong(String timeStr) {
        String[] s = timeStr.split(":");
        int hour = Integer.parseInt(s[0]);
        int min = Integer.parseInt(s[1]);
        String[] ss = s[2].split(",");
        int sec = Integer.parseInt(ss[0]);
        int mill = Integer.parseInt(ss[1]);
        return hour * 3600000 + min * 60 * 1000 + sec * 1000 + mill;
    }

    public static long strToLong1(String timeStr) {
        String[] s = timeStr.split(":");
        int min = Integer.parseInt(s[0]);
        String[] ss = s[1].split("\\.");
        int sec = Integer.parseInt(ss[0]);
        int mill = Integer.parseInt(ss[1]);
        return min * 60 * 1000 + sec * 1000 + mill * 10;
    }
}
