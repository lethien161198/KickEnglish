package com.example.myapplication.Common.basemvp;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
//        if (getSupportFragmentManager().getBackStackEntryCount() > 2) {
//            getSupportFragmentManager().popBackStack();
//            return;
//        } else if (getSupportFragmentManager().getBackStackEntryCount() == 2) {
//            EventBus.getDefault()
//                    .postSticky(new CustomEvent(1, 0));
//            getSupportFragmentManager().popBackStack();
//            return;
//        } else {
        super.onBackPressed();
//            finish();
//        }

    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        init();
    }

    protected abstract int getLayoutResourceId();

    public abstract void init();

}
