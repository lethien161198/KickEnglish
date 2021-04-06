package com.example.myapplication.Modules.Home.Fragment;

import com.example.myapplication.Common.SharedPreferenceHelper;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Modules.API.ApiUser;
import com.example.myapplication.Common.basemvp.Interactor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AccountInteractor extends Interactor<AccountContract.Presenter> implements AccountContract.Interactor {
    private ApiUser apiUser = createService(ApiUser.class);

    @Override
    public void getUser() {
        UserDTO userDTO = SharedPreferenceHelper.getUser(Utilities.SHARE_USER);
        getPresenter().onFinishGetUser(userDTO);
    }

    @Override
    public void logOut() {
        Observable<BodyResponseDTO<Data>> observable = apiUser.logOut();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BodyResponseDTO<Data>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BodyResponseDTO<Data> dataBodyResponseDTO) {
                        getPresenter().onFinishLogout(dataBodyResponseDTO);
                        getPresenter().onSuccess(dataBodyResponseDTO.getMessage());
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
