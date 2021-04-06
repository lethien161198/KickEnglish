package com.example.myapplication.Modules.Setting;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Setting.UserDTO;

import okhttp3.MultipartBody;

public interface SettingContract {
    interface View extends MVP.View<Presenter> {
        void loadInfoUser(UserDTO userDTO);

        void saveSuccess();
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getUserDTO();

        void saveUserDTO(String fn, String ln, String p, String dob, String gen, String national, String des, MultipartBody.Part filePart, String lang);

        void onFinish();

    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void updateUserDTO(String fn, String ln, String p, String dob, String gen, String national, String des, MultipartBody.Part filePart, String lang);
    }
}
