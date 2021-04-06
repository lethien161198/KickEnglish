package com.example.myapplication.Modules.Setting;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Setting.ChangePasswordDTO;

public interface ChangePasswordContract {
    interface View extends MVP.View<Presenter> {

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void changePass(ChangePasswordDTO changePasswordDTO);

        void onFinish();
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void updatePass(ChangePasswordDTO changePasswordDTO);
    }
}
