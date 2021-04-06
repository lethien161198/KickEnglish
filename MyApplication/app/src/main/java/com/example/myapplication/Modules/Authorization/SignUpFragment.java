package com.example.myapplication.Modules.Authorization;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Models.Authorization.SignUpRequest;
import com.example.myapplication.R;

public class SignUpFragment extends FragmentView<SignUpContract.Presenter> implements SignUpContract.View {
    private EditText user, pass, email;
    private Button signup;
    private TextView signin;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_signup;
    }


    @Override
    public void navigateSignIn() {
        navigateReplaced(AppNavigator.ROUTE_SIGN_IN);
    }

    @Override
    public void init(View view) {
        user = view.findViewById(R.id.user);
        pass = view.findViewById(R.id.pass);
        email = view.findViewById(R.id.email);
        signup = view.findViewById(R.id.SignUp);
        signin = view.findViewById(R.id.gotosignin);
        signup.setOnClickListener(v -> {
            SignUpRequest newS = new SignUpRequest(email.getText().toString(), "",
                    "", pass.getText().toString(), "", user.getText().toString());
            getPresenter().validateInfor(newS);
        });

        signin.setOnClickListener(v -> navigateSignIn());
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
