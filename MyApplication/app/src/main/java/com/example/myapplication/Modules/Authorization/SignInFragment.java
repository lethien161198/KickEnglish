package com.example.myapplication.Modules.Authorization;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Models.Authorization.SignInRequest;
import com.example.myapplication.Modules.Home.HomeActivity;
import com.example.myapplication.R;

public class SignInFragment extends FragmentView<SignInContract.Presenter> implements SignInContract.View {
    private EditText user, pass;
    private Button signin;
    private TextView signup;
    private ImageView imageView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_signin;
    }

    @Override
    public void navigateHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void init(View view) {
        user = view.findViewById(R.id.user);
        pass = view.findViewById(R.id.pass);
        signin = view.findViewById(R.id.SignIn);
        signup = view.findViewById(R.id.gotosignup);
        imageView = view.findViewById(R.id.imageview);

        signup.setOnClickListener(v -> {
            navigateReplaced(AppNavigator.ROUTE_SIGN_UP);
        });
        signin.setOnClickListener(v -> {
            SignInRequest request = new SignInRequest(pass.getText().toString(), user.getText().toString());
            getPresenter().validate(request);
        });

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
    }
}
