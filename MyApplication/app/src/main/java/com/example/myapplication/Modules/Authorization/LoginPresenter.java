package com.example.myapplication.Modules.Authorization;


import android.os.Bundle;

import com.example.myapplication.Common.SharedPreferenceHelper;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Authorization.JwtResponse;
import com.example.myapplication.Models.Authorization.SignInRequest;
import com.example.myapplication.Common.basemvp.Presenter;

public class LoginPresenter extends Presenter<SignInContract.View, SignInContract.Interactor> implements SignInContract.Presenter {
    public String message = "Failed";

    public LoginPresenter(SignInContract.View view, SignInContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void validate(SignInRequest signInRequest) {
        getInteractor().getAPI(signInRequest);
    }

    @Override
    public void tokenFailed(String message) {
        getView().showTokenFailed(message);
    }

    @Override
    public void onSuccess(String message) {
        getView().showSuccess(message);
    }

    @Override
    public void onError(String message) {
        getView().showError(message);
    }


    @Override
    public void onFinishSignIn(JwtResponse result) {
        if (result != null) {
            SharedPreferenceHelper.setString(Utilities.SHARE_TOKEN, result.getToken());
            getView().navigateHome();
            SharedPreferenceHelper.setString(Utilities.SHARE_LANG, result.getLang());
            Utilities.token = result.getToken();
        }
    }
}
