package com.example.myapplication.Common;

import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class LoadImage {
    public static void Load(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url).error(R.drawable.ic_launcher_background)
                .into(img);
    }

    public static void Load(Fragment context, String url, ImageView img) {
        Glide.with(context)
                .load(url)
                .into(img);
    }

}
