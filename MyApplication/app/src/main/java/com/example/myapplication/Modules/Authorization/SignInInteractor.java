package com.example.myapplication.Modules.Authorization;

import android.os.Handler;
import android.util.Log;

import com.example.myapplication.Models.Authorization.JwtResponse;
import com.example.myapplication.Models.Authorization.SignInRequest;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Modules.API.ApiAccount;
import com.example.myapplication.Common.basemvp.Interactor;

import retrofit2.Call;
import retrofit2.Callback;

public class SignInInteractor extends Interactor<SignInContract.Presenter> implements SignInContract.Interactor {
    private ApiAccount apiAccount = createService(ApiAccount.class);


    @Override
    public void getAPI(final SignInRequest signInRequest) {
        new Handler().postDelayed(() -> {
            Call<BodyResponseDTO<JwtResponse>> call = apiAccount.SignIn(signInRequest);
            call.enqueue(new Callback<BodyResponseDTO<JwtResponse>>() {
                @Override
                public void onResponse(Call<BodyResponseDTO<JwtResponse>> call, retrofit2.Response<BodyResponseDTO<JwtResponse>> response) {
                    if (response.body() != null) {

                        getPresenter().onFinishSignIn(response.body().getData());
                        getPresenter().onSuccess(response.body().getMessage());
                    }

                }

                @Override
                public void onFailure(Call<BodyResponseDTO<JwtResponse>> call, Throwable t) {
                    getPresenter().onError(t.getMessage());
                    Log.d("a", t.getMessage());
                }
            });
        }, 1000);
    }


}
