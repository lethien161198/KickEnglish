package com.example.myapplication.Modules.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Modules.Pronunciation.PlayAudio.AudioActivity;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private AudioActivity audioActivity;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, AudioActivity audioActivity) {
        super(fm, behavior);
        this.audioActivity = audioActivity;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return audioActivity.getLyricFragment();
            default:
                return audioActivity.getPlayerFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

}
