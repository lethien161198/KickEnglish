package com.example.myapplication.Modules.Home.Fragment;

import android.os.Bundle;

import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Common.basemvp.Presenter;

public class AccountPresenter extends Presenter<AccountContract.View, AccountContract.Interactor> implements AccountContract.Presenter {

    public AccountPresenter(AccountContract.View view, AccountContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void User() {
        getInteractor().getUser();
    }

    @Override
    public void onClickLogOut() {
        getInteractor().logOut();
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
    public void onFinishLogout(BodyResponseDTO<Data> result) {
        if (result != null && result.getMessage().equals("Success")) {
            getView().navigateLogin();
        }
    }

    @Override
    public void onFinishGetUser(UserDTO userDTO) {
        getView().loadUser(userDTO);
    }
}
