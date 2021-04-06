package com.example.myapplication.Modules.Authorization;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Authorization.JwtResponse;
import com.example.myapplication.Models.Authorization.SignInRequest;

public interface SignInContract {
    interface View extends MVP.View<Presenter> {
        void navigateHome();
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void validate(SignInRequest signInRequest);

        void onFinishSignIn(JwtResponse result);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void getAPI(SignInRequest signInRequest);
    }
}
