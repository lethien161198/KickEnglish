package com.example.myapplication.Modules.Authorization;

import android.util.Log;

import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.Authorization.SignUpRequest;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Modules.API.ApiAccount;
import com.example.myapplication.Common.basemvp.Interactor;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpInteractor extends Interactor<SignUpContract.Presenter> implements SignUpContract.Interactor {
    private ApiAccount apiAccount = createService(ApiAccount.class);

    @Override
    public void signUpAPI(SignUpRequest signUpRequest) {
        Call<BodyResponseDTO<Data>> call = apiAccount.SignUp(signUpRequest);
        call.enqueue(new Callback<BodyResponseDTO<Data>>() {
            @Override
            public void onResponse(Call<BodyResponseDTO<Data>> call, retrofit2.Response<BodyResponseDTO<Data>> response) {
                if (response.body() != null) {
                    getPresenter().onFinishSignUp(response.body());
                    getPresenter().onSuccess(response.body().getMessage());
                } else getPresenter().onError(response.message());
            }

            @Override
            public void onFailure(Call<BodyResponseDTO<Data>> call, Throwable t) {
                getPresenter().onError(t.getMessage());
                Log.d("a", t.getMessage());
            }
        });
    }
}
