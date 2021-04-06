package com.example.myapplication.Modules.Setting;

import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Setting.ChangePasswordDTO;
import com.example.myapplication.Modules.API.ApiUser;
import com.example.myapplication.Common.basemvp.Interactor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ChangePasswordInteractor extends Interactor<ChangePasswordContract.Presenter> implements ChangePasswordContract.Interactor {
    private ApiUser apiUser = createService(ApiUser.class);

    @Override
    public void updatePass(ChangePasswordDTO changePasswordDTO) {
        Observable<Response<BodyResponseDTO<Data>>> observable = apiUser.changePass(changePasswordDTO);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<Data>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<Data>> bodyResponseDTOResponse) {
                        if (bodyResponseDTOResponse.body() != null) {
                            getPresenter().onFinish();
                            getPresenter().onSuccess(bodyResponseDTOResponse.body().getMessage());
                        } else if (bodyResponseDTOResponse.code() == 401)
                            getPresenter().tokenFailed(bodyResponseDTOResponse.code() + "");
                        else getPresenter().onError(bodyResponseDTOResponse.message());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getPresenter().onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
