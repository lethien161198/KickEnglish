package com.example.myapplication.Modules.Setting;

import android.os.Bundle;

import com.example.myapplication.Common.SharedPreferenceHelper;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Common.basemvp.Presenter;

import okhttp3.MultipartBody;

public class SettingPresenter extends Presenter<SettingContract.View, SettingContract.Interactor> implements SettingContract.Presenter {

    public SettingPresenter(SettingContract.View view, SettingContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getUserDTO() {
        UserDTO userDTO = SharedPreferenceHelper.getUser(Utilities.SHARE_USER);
        getView().loadInfoUser(userDTO);
    }

    @Override
    public void saveUserDTO(String fn, String ln, String p, String dob, String gen, String national, String des, MultipartBody.Part filePart, String lang) {
        getInteractor().updateUserDTO(fn, ln, p, dob, gen, national, des, filePart, lang);
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
