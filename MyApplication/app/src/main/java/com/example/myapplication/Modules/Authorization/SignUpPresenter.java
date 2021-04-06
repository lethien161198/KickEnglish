package com.example.myapplication.Modules.Authorization;

import android.os.Bundle;

import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.Authorization.SignUpRequest;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Common.basemvp.Presenter;

public class SignUpPresenter extends Presenter<SignUpContract.View, SignUpContract.Interactor> implements SignUpContract.Presenter {
    public String message = "Failed";

    public SignUpPresenter(SignUpContract.View view, SignUpContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void validateInfor(SignUpRequest signUpRequest) {
        getInteractor().signUpAPI(signUpRequest);
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
    public void onFinishSignUp(BodyResponseDTO<Data> result) {

    }
}
