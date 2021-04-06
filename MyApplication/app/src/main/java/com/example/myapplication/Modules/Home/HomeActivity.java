package com.example.myapplication.Modules.Home;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Common.SharedPreferenceHelper;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.BaseActivity;
import com.example.myapplication.Components.CustomViewPager;
import com.example.myapplication.Models.CustomEvent;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Modules.Adapter.BottomAdapter;
import com.example.myapplication.Modules.Home.Fragment.MoreFragment;
import com.example.myapplication.Modules.Pronunciation.PlayAudio.AudioActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeActivity extends BaseActivity {
    private static BottomNavigationView navigation;
    private CustomViewPager viewPager;

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnCustomEvent(CustomEvent customEvent) {
        if (customEvent.getA() == 1) {
            navigation.setVisibility(View.VISIBLE);
        } else navigation.setVisibility(View.GONE);

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                viewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_more:
                viewPager.setCurrentItem(1);

                return true;
        }
        return false;
    };

    private void setupViewPager(CustomViewPager viewPager) {
        BottomAdapter adapter = new BottomAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        MoreFragment moreFragment = new MoreFragment();
        Fragment homeFragment = (Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_HOME, null);
        adapter.addFragment(homeFragment);
        adapter.addFragment(moreFragment);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void getLanguage() {
        String lang = SharedPreferenceHelper.getString(Utilities.SHARE_LANG, "en");
        Utilities.setLocale(lang, getResources());
    }

    public void goToPlayAudioActivity(MediaDTO mediaDTO) {
        if (mediaDTO != null) {
            Intent intent = new Intent(HomeActivity.this, AudioActivity.class);
            intent.putExtra(Utilities.EXTRA_PLAYAUDIO, mediaDTO);
            startActivity(intent);
        }

    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    public void init() {
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        getLanguage();
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //loadFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_HOME));
    }
}