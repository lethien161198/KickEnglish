package com.example.myapplication.Modules.Home.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Common.LoadImage;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Modules.Authorization.MainActivity;
import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends FragmentView<AccountContract.Presenter> implements AccountContract.View {
    private TextView setting, back, title, logout;
    private ImageView imgBack;
    private CircleImageView avtUser;
    private TextView txtName, txtFree;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    public void init(View view) {
        back = view.findViewById(R.id.txtback);
        logout = view.findViewById(R.id.logout);
        title = view.findViewById(R.id.txtTitle);
        imgBack = view.findViewById(R.id.imageBack);
        txtFree = view.findViewById(R.id.txtFree);
        txtName = view.findViewById(R.id.txtName);
        avtUser = view.findViewById(R.id.avtUser);
        getPresenter().User();
        back.setOnClickListener(v -> {
            getActivity().onBackPressed();
            showBottomBar(1, R.drawable.backg);
        });
        title.setText("My Account");
        imgBack.setBackgroundResource(R.drawable.ic_baseline_arrow_back_ios_24);
        setting = view.findViewById(R.id.setting);
        setting.setOnClickListener(v -> navigateSetting());
        logout.setOnClickListener(v -> getPresenter().onClickLogOut());

        showBottomBar(0, R.color.black);

    }

    public void navigateSetting() {
        navigateReplaced(AppNavigator.ROUTE_SETTINGS);
    }

    @Override
    public void loadUser(UserDTO userDTO) {
        String url = BuildConfig.BASE_URL + userDTO.getImgUrl();
        txtName.setText(userDTO.getFirstName() + " " + userDTO.getLastName());
        txtFree.setText(userDTO.getSubscriptionPlan().getName());
        LoadImage.Load(getContext(), url, avtUser);
    }


    @Override
    public void navigateLogin() {
        getActivity().finish();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void showSuccess(String message) {
        showToast(message);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @Override
    public void showTokenFailed(String message) {
        showToast(message);
        logOut(getActivity());
    }
}