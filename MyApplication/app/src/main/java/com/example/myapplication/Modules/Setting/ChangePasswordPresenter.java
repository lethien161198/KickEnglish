package com.example.myapplication.Modules.Setting;


import android.os.Bundle;

import com.example.myapplication.Models.Setting.ChangePasswordDTO;
import com.example.myapplication.Common.basemvp.Presenter;

public class ChangePasswordPresenter extends Presenter<ChangePasswordContract.View, ChangePasswordContract.Interactor> implements ChangePasswordContract.Presenter {

    public ChangePasswordPresenter(ChangePasswordContract.View view, ChangePasswordContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void changePass(ChangePasswordDTO changePasswordDTO) {
        getInteractor().updatePass(changePasswordDTO);
    }

    @Override
    public void onFinish() {

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
}
