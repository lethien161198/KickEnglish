package com.example.myapplication.Common.basemvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Models.CustomEvent;
import com.example.myapplication.Modules.Authorization.MainActivity;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;

public abstract class FragmentView<P extends MVP.Presenter> extends Fragment implements MVP.View<P> {
    private P presenter;

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayoutId(), container, false);
        init(view);
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void init(View view);

    @Override
    public void setParameters(@Nullable Bundle args) {
        setArguments(args);
    }

    public void navigate(String route) {
        navigate(route, null);
    }

    @Override
    public void navigate(String route, Bundle parameters) {
        MVP.View view = AppNavigator.viewWithRoute(route, parameters);
        if (view == null) {
            return;
        }
        addFragment((Fragment) view);
    }

    public void navigateReplaced(String route) {
        navigateReplaced(route, null);
    }

    @Override
    public void navigateReplaced(String route, Bundle parameters) {
        MVP.View view = AppNavigator.viewWithRoute(route, parameters);
        if (view == null) {
            return;
        }
        replaceFragment((Fragment) view);
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showBottomBar(long check, int background) {
        EventBus.getDefault()
                .postSticky(new CustomEvent(check, background));
    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void logOut(Activity activity) {
        getActivity().finish();
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showTokenFailed(String message) {

    }

    @Override
    public void showError(String message) {

    }
}
