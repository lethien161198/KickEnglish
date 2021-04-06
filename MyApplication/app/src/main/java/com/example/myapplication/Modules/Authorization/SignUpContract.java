package com.example.myapplication.Modules.Authorization;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.Authorization.SignUpRequest;
import com.example.myapplication.Models.BodyResponseDTO;

public interface SignUpContract {
    interface View extends MVP.View<Presenter> {
        void navigateSignIn();
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void validateInfor(SignUpRequest signUpRequest);

        void onFinishSignUp(BodyResponseDTO<Data> result);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void signUpAPI(SignUpRequest signUpRequest);
    }
}
