package com.example.myapplication.Modules.Home.Fragment;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Setting.UserDTO;

public interface AccountContract {
    interface View extends MVP.View<Presenter> {
        void loadUser(UserDTO userDTO);

        void navigateLogin();

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void User();

        void onClickLogOut();

        void onFinishLogout(BodyResponseDTO<Data> result);

        void onFinishGetUser(UserDTO userDTO);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void getUser();

        void logOut();
    }
}
